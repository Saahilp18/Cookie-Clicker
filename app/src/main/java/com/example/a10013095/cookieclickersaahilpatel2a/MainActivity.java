package com.example.a10013095.cookieclickersaahilpatel2a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    ImageView cookieImage, upgradeBackground, ogGrandma, ogGrandpa;
    TextView cookieCountTV, title, grandmaCostTV, grandmaCountTV, grandpaCostTV, grandpaCountTV;
    ArrayList<oneMaker> onesList;
    ConstraintLayout layout;
    Button grandma, grandpa;
    int grandmaCount, grandmaXValue, grandmaNeeded, grandpaNeeded, grandpaCount, grandpaXValue;
    ArrayList<grandmaImages> grandmaImagesList;
    ArrayList<grandpaImages> grandpaImagesList;
    AtomicInteger atomicCookies;
    boolean go;

    Animation fadeIn;
    Animation fadeOut;
    Animation titleFadeIn;
    Boolean grandmaFirst = true;
    Boolean grandmaDone = true;
    Boolean grandpaFirst = true;
    Boolean grandpaDone = true;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        go = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cookieImage = findViewById(R.id.id_cookieImage);
        grandpa = findViewById(R.id.id_grandpaButton);
        cookieCountTV = findViewById(R.id.id_cookieCount);
        ogGrandpa = findViewById(R.id.id_OGGrandpa);
        title = findViewById(R.id.id_title);
        title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        layout = findViewById(R.id.id_layout);
        grandma = findViewById(R.id.id_grandmaButton);
        upgradeBackground = findViewById(R.id.id_upgradeBackground);
        ogGrandma = findViewById(R.id.id_OGGrandma);
        grandpaCountTV = findViewById(R.id.id_grandpaCountTV);
        grandpaCostTV = findViewById(R.id.id_grandpaCostTV);
        ogGrandma.setVisibility(View.INVISIBLE);
        grandmaCountTV = findViewById(R.id.id_grandmaCount);
        grandmaCount = 0;

        animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();


        titleFadeIn = new AlphaAnimation(0, 1);
        titleFadeIn.setInterpolator(new DecelerateInterpolator());
        titleFadeIn.setDuration(5000);
        title.setAnimation(titleFadeIn);
        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(700);
        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);

        grandma.setVisibility(View.INVISIBLE);
        grandpa.setVisibility(View.INVISIBLE);
        grandma.setClickable(false);
        grandpa.setClickable(false);
        grandmaCostTV = findViewById(R.id.id_grandmaCostTV);
        grandmaImagesList = new ArrayList<>();
        onesList = new ArrayList<>();
        grandpaImagesList = new ArrayList<>();
        new Checker().start();
        grandpaXValue = 0;
        grandmaNeeded = 5;
        grandpaNeeded = 10;
        grandmaXValue = 40;
        grandpaCount = 0;
        atomicCookies = new AtomicInteger(0);
        final ScaleAnimation cookieAnimation = new ScaleAnimation(0.92f, 1.0f, 0.92f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        cookieAnimation.setDuration(300);

        new afkAdder().start();


        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atomicCookies.addAndGet(1);
                cookieImage.startAnimation(cookieAnimation);
                cookieCountTV.setText("" + atomicCookies.get());
                onesList.add(new oneMaker());
                if (onesList.size() >= 10) {
                    for (int i = 0; i < 5; i++)
                        onesList.remove(i);
                }

              /*  if (atomicCookies.get() >= grandmaNeeded) {
                    grandma.setClickable(true);
                    grandma.setVisibility(View.VISIBLE);
                } else
                    grandma.setVisibility(View.INVISIBLE);
                if (atomicCookies.get() >= grandpaNeeded) {
                    grandpa.setClickable(true);
                    grandpa.setVisibility(View.VISIBLE);
                } else
                    grandpa.setVisibility(View.INVISIBLE);*/

            }
        });
        grandma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (atomicCookies.get() >= grandmaNeeded) {
                    grandmaCount++;
                    atomicCookies.getAndAdd(grandmaNeeded * -1);
/*                    if (atomicCookies.get() < grandmaNeeded)
                        grandma.setVisibility(View.INVISIBLE);*/
                    grandmaNeeded *= 1.3;
                    Toast.makeText(MainActivity.this, "Grandma Bought!", Toast.LENGTH_SHORT).show();
                    grandmaCostTV.setText("Grandma Cost: " + grandmaNeeded + " Cookies");
                    cookieCountTV.setText("" + atomicCookies.get());
                    grandmaCountTV.setText("You Own " + grandmaCount + " Grandma(s)");
                    grandmaImagesList.add(new grandmaImages(grandmaXValue));
                    grandmaXValue += 40;
                    if (grandmaCount == 10)
                        grandmaXValue = 10;

                }
            }
        });

        grandpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (atomicCookies.get() >= grandpaNeeded) {
                    grandpaCount++;
                    atomicCookies.getAndAdd(grandpaNeeded * -1);
                   /* if (atomicCookies.get() < grandpaNeeded)
                        grandpa.setVisibility(View.INVISIBLE);*/
                    grandpaNeeded *= 1.4;
                    Toast.makeText(MainActivity.this, "Grandpa Bought!", Toast.LENGTH_SHORT).show();
                    grandpaCostTV.setText("Grandpa Cost: " + grandpaNeeded + " Cookies");
                    cookieCountTV.setText("" + atomicCookies.get());
                    grandpaCountTV.setText("You Own " + grandpaCount + " Grandpa(s)");
                    grandpaImagesList.add(new grandpaImages(grandpaXValue));
                    grandpaXValue += 40;
                    if (grandpaCount == 10)
                        grandpaXValue = 10;

                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        go = false;
    }

    public void addOnGrandma() {
        atomicCookies.getAndAdd(grandmaCount * 1);
        //  Log.d("TAG", "Thread: " + atomicCookies.get());
    }

    public void addOnGrandpa() {
        atomicCookies.getAndAdd(grandpaCount * 3);
        //  Log.d("TAG", "Thread: " + atomicCookies.get());
    }

    public class oneMaker {
        TextView one;

        public oneMaker() {
            int widthRange;
            int ran = (int) (Math.random() * 2) + 1;
            if (ran == 1)
                widthRange = -1 * (int) (Math.random() * 80);
            else
                widthRange = (int) (Math.random() * 80);
            one = new TextView(MainActivity.this);
            one.setId(View.generateViewId());
            one.setText("+1");
            one.setTextSize(20);
            one.setTextColor(Color.WHITE);
            ConstraintLayout.LayoutParams p = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            one.setLayoutParams(p);
            one.setX(one.getX() + widthRange);
            one.setY(one.getY());

            layout.addView(one);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout);
            constraintSet.connect(one.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
            constraintSet.connect(one.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(one.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
            constraintSet.connect(one.getId(), ConstraintSet.BOTTOM, cookieImage.getId(), ConstraintSet.BOTTOM);
            constraintSet.applyTo(layout);

            ObjectAnimator animator = ObjectAnimator.ofFloat(one, "translationY", -80f);
            animator.setDuration(400);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    one.setText("");
                }
            });
            animator.start();

        }
    }

    public class grandmaImages {
        ImageView image;

        public grandmaImages(int xValue) {
            image = new ImageView(MainActivity.this);
            image.setId(View.generateViewId());
            image.setImageResource(R.drawable.grandma);
            ConstraintLayout.LayoutParams p = new ConstraintLayout.LayoutParams(380, 300);
            image.setLayoutParams(p);
            image.setX(xValue);
            image.setY(image.getY());
            layout.addView(image);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout);
            constraintSet.connect(image.getId(), ConstraintSet.TOP, ogGrandma.getId(), ConstraintSet.TOP);
            constraintSet.connect(image.getId(), ConstraintSet.LEFT, ogGrandma.getId(), ConstraintSet.LEFT);
            constraintSet.connect(image.getId(), ConstraintSet.RIGHT, ogGrandma.getId(), ConstraintSet.RIGHT);
            constraintSet.connect(image.getId(), ConstraintSet.BOTTOM, ogGrandma.getId(), ConstraintSet.BOTTOM);
            constraintSet.applyTo(layout);

        }
    }

    public class grandpaImages {
        ImageView image;

        public grandpaImages(int xValue) {
            image = new ImageView(MainActivity.this);
            image.setId(View.generateViewId());
            image.setImageResource(R.drawable.grandpa);
            ConstraintLayout.LayoutParams p = new ConstraintLayout.LayoutParams(ogGrandma.getWidth(), ogGrandpa.getHeight());
            image.setLayoutParams(p);
            image.setX(-1 * xValue);
            image.setY(image.getY());
            layout.addView(image);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout);
            constraintSet.connect(image.getId(), ConstraintSet.TOP, ogGrandpa.getId(), ConstraintSet.TOP);
            constraintSet.connect(image.getId(), ConstraintSet.LEFT, ogGrandpa.getId(), ConstraintSet.LEFT);
            constraintSet.connect(image.getId(), ConstraintSet.RIGHT, ogGrandpa.getId(), ConstraintSet.RIGHT);
            constraintSet.connect(image.getId(), ConstraintSet.BOTTOM, ogGrandpa.getId(), ConstraintSet.BOTTOM);
            constraintSet.applyTo(layout);

        }
    }

    public class afkAdder extends Thread {
        @Override
        public void run() {
            super.run();
            while (go) {
                try {
                    Thread.sleep(2000);
                    addOnGrandma();
                    addOnGrandpa();
                    cookieCountTV.post(new Runnable() {
                        @Override
                        public void run() {
                            cookieCountTV.setText("" + atomicCookies.get());
                        }
                    });
                } catch (Exception e) {
                }
            }
        }

    }

    public class Checker extends Thread {
        public void run() {
            super.run();
            while (go) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                grandma.post(new Runnable() {
                    @Override
                    public void run() {
                        if (atomicCookies.get() >= grandmaNeeded) {
                            grandma.setClickable(true);
                            grandma.setVisibility(View.VISIBLE);
                        } else
                            grandma.setVisibility(View.INVISIBLE);


                        if (atomicCookies.get() >= grandmaNeeded) {
                            grandmaDone = true;
                            if (grandmaFirst) {
                                grandma.startAnimation(fadeIn);
                                grandmaFirst = false;
                            }
                        }
                        if (atomicCookies.get() < grandmaNeeded) {
                            grandmaFirst = true;
                            if (grandmaDone) {
                                grandma.startAnimation(fadeOut);
                                grandmaDone = false;
                            }
                        }
                        if (atomicCookies.get() >= grandmaNeeded) {
                            grandma.setClickable(true);
                            grandma.setVisibility(View.VISIBLE);
                        } else
                            grandma.setVisibility(View.INVISIBLE);
                    }
                });


                grandpa.post(new Runnable() {
                    @Override
                    public void run() {
                        if (atomicCookies.get() >= grandpaNeeded) {
                            grandpa.setClickable(true);
                            grandpa.setVisibility(View.VISIBLE);
                        } else
                            grandpa.setVisibility(View.INVISIBLE);


                        if (atomicCookies.get() >= grandpaNeeded) {
                            grandpaDone = true;
                            if (grandpaFirst) {
                                grandpa.startAnimation(fadeIn);
                                grandpaFirst = false;
                            }
                        }
                        if (atomicCookies.get() < grandpaNeeded) {
                            grandpaFirst = true;
                            if (grandpaDone) {
                                grandpa.startAnimation(fadeOut);
                                grandpaDone = false;
                            }
                        }
                        if (atomicCookies.get() >= grandpaNeeded) {
                            grandpa.setClickable(true);
                            grandpa.setVisibility(View.VISIBLE);
                        } else
                            grandpa.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }
    }

}
