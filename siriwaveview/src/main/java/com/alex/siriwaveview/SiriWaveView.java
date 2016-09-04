package com.alex.siriwaveview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Alex on 6/25/2016.
 */
public class SiriWaveView extends View {

    private Path mPath;
    private Paint mPaint;

    float frequency = 1.5f;
    float IdleAmplitude = 0.00f;
    int waveNumber = 2;
    float phaseShift = 0.15f;
    float density = 10.0f;
    float initialPhaseOffset = 0.0f;
    float waveHeight;
    float waveVerticalPosition = 2;
    int waveColor;
    float phase;
    float amplitude;
    float level = 1.0f;

    ObjectAnimator mAmplitudeAnimator;

    public SiriWaveView(Context context) {
        super(context);
        if (!isInEditMode())
            init(context, null);
    }

    public SiriWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(context, attrs);
    }

    public SiriWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, com.alex.siriwaveview.R.styleable.SiriWaveView);
        frequency = a.getFloat(com.alex.siriwaveview.R.styleable.SiriWaveView_waveFrequency, frequency);
        IdleAmplitude = a.getFloat(com.alex.siriwaveview.R.styleable.SiriWaveView_waveIdleAmplitude, IdleAmplitude);
        phaseShift = a.getFloat(com.alex.siriwaveview.R.styleable.SiriWaveView_wavePhaseShift, phaseShift);
        initialPhaseOffset = a.getFloat(com.alex.siriwaveview.R.styleable.SiriWaveView_waveInitialPhaseOffset, initialPhaseOffset);
        waveHeight = a.getDimension(com.alex.siriwaveview.R.styleable.SiriWaveView_waveHeight, waveHeight);
        waveColor = a.getColor(com.alex.siriwaveview.R.styleable.SiriWaveView_waveColor, waveColor);
        waveVerticalPosition = a.getFloat(com.alex.siriwaveview.R.styleable.SiriWaveView_waveVerticalPosition, waveVerticalPosition);
        waveNumber = a.getInteger(R.styleable.SiriWaveView_waveAmount, waveNumber);

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(waveColor);

        a.recycle();
        setupAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        updatePath();
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        startOrCancelAngleAnim();
    }

    private void setupAnimation() {
        if (mAmplitudeAnimator == null) {
            mAmplitudeAnimator = ObjectAnimator.ofFloat(this, "amplitude", 1f);
            mAmplitudeAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            mAmplitudeAnimator.setRepeatMode(ObjectAnimator.INFINITE);
            mAmplitudeAnimator.setInterpolator(new LinearInterpolator());
        }
        if (!mAmplitudeAnimator.isRunning()) {
            mAmplitudeAnimator.start();
        }
    }

    public void stopAnimation() {
        if (mAmplitudeAnimator != null) {
            mAmplitudeAnimator.cancel();
        }
    }

    public void startAnimation() {
        if (mAmplitudeAnimator != null) {
            mAmplitudeAnimator.start();
        }
    }

    private void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
        invalidate();
    }

    private void updatePath() {
        mPath.reset();

        phase += phaseShift;
        amplitude = Math.max(level, IdleAmplitude);

        for (int i = 0; i < waveNumber; i++) {
            float halfHeight = getHeight() / waveVerticalPosition;
            float width = getWidth();
            float mid = width / 2.0f;

            float maxAmplitude = halfHeight - (halfHeight - waveHeight);

            // Progress is a value between 1.0 and -0.5, determined by the current wave idx, which is used to alter the wave's amplitude.
            float progress = 1.0f - (float) i / waveNumber;
            float normedAmplitude = (1.5f * progress - 0.5f) * amplitude;

            float multiplier = (float) Math.min(1.0, (progress / 3.0f * 2.0f) + (1.0f / 3.0f));

            for (int x = 0; x < width + density; x += density) {
                float scaling = (float) (-Math.pow(1 / mid * (x - mid), 2) + 1);

                float y = (float) (scaling * maxAmplitude * normedAmplitude * Math.sin(2 * Math.PI * (x / width) * frequency + phase + initialPhaseOffset) + halfHeight);

                if (x == 0) {
                    mPath.moveTo(x, y);
                } else {
                    mPath.lineTo(x, y);
                }
            }
        }

        //mPath.lineTo(getWidth(), getHeight());
        //mPath.lineTo(0, getHeight());

        //mPath.close();
    }

    public void startOrCancelAngleAnim() {
        if (isViewVisible()) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    private boolean isViewVisible() {
        return getVisibility() == VISIBLE && getAlpha() * 255 > 0;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    public void setWaveColor(int waveColor) {
        mPaint.setColor(waveColor);
        invalidate();
    }
    public void setStrokeWidth(float strokeWidth){
        mPaint.setStrokeWidth(strokeWidth);
        invalidate();
    }
}