package com.github.daemontus.ar.libgdx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.github.daemontus.ar.vuforia.VuforiaRenderer;
import com.vuforia.Device;
import com.vuforia.Matrix44F;
import com.vuforia.RenderingPrimitives;
import com.vuforia.Tool;
import com.vuforia.TrackableResult;


/**
 * Class responsible for rendering and scene transformations.
 */
public class SpriteBatchRenderer {
    private static final String LOG = "RENDERER";

    private VuforiaRenderer vuforiaRenderer;
    private RenderingPrimitives mRenderingPrimitives = null;

    Texture texture;
    int textureWidth = 100;
    int textureHeight = 100;
    private SpriteBatch batch;

    public SpriteBatchRenderer(VuforiaRenderer arRenderer) {

        this.vuforiaRenderer = arRenderer;
        batch = new SpriteBatch(1);

        texture = new Texture(Gdx.files.internal("badlogic.jpg"));

        Device device = Device.getInstance();
        device.setViewerActive(false); // Indicates if the app will be using a viewer, stereo mode and initializes the rendering primitives
        device.setMode(Device.MODE.MODE_AR); // Select if we will be in AR or VR mode

        mRenderingPrimitives = Device.getInstance().getRenderingPrimitives();
    }

    public void render(Display display, float delta) {
        GL20 gl = Gdx.gl;

        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        TrackableResult[] trackables = null;

        if (vuforiaRenderer.mIsActive) {
            //render camera background and find targets
            trackables = vuforiaRenderer.processFrame();
        }


        if (trackables != null && trackables.length > 0) {
            //transform all content
            TrackableResult trackable = trackables[0];

            Matrix44F modelViewMatrix = Tool.convertPose2GLMatrix(trackable.getPose());
            float[] raw = modelViewMatrix.getData();

            Matrix4 transformMatrix = new Matrix4(raw);
            Matrix4 projectionMatrix = new Matrix4(vuforiaRenderer.projectionMatrix.getData());

            batch.setProjectionMatrix(projectionMatrix);
            batch.setTransformMatrix(transformMatrix);
            batch.begin();
            batch.draw(texture,-(textureWidth/2),-(textureHeight/2),textureWidth,textureHeight);
            batch.end();

        }


        }


    public void dispose() {
        batch.dispose();
    }

}
