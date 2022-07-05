package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Objects;

public class AR extends AppCompatActivity {

    private ArFragment arCam; //object of ArFragment Class

    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    String ProjectName=null;


    public static boolean checkSystemSupport(Activity activity) {

        //checking whether the API version of the running Android >= 24 that means Android Nougat 7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            //checking whether the OpenGL version >= 3.0
            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);



        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
            ProjectName = extras.getString("projectName");

        switch (ProjectName){
            case "Monitor Lizard":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.monitorlizard)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Rabbit":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.rabbit)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Bee":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.bee)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Jellyfish":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.jellyfish)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Komodo Dragon":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.komododragon)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Ulysses Butterfly":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.ulyssesbutterfly)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Octopus":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.ocutopus)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "SeaHorse":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.seahorse)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Demo":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.gfg_gold_text_stand_2)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
        }




    }

    private void addModel(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        // Creating a AnchorNode with a specific anchor
        anchorNode.setParent(arCam.getArSceneView().getScene());
        //attaching the anchorNode with the ArFragment
        TransformableNode model = new TransformableNode(arCam.getTransformationSystem());
        model.setParent(anchorNode);
        //attaching the anchorNode with the TransformableNode
        model.setRenderable(modelRenderable);
        //attaching the 3d model with the TransformableNode that is already attached with the node
        model.select();
    }
}