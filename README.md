# SIRIWaveView

![alt tag](https://github.com/alexgomes09/SIRIWaveView/blob/master/captures/screen_capture.gif)

## Usage
```
<com.alex.siriwaveview.SiriWaveView
        android:id="@+id/siriWaveView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:waveAmount="4"
        app:waveColor="@android:color/white"
        app:waveFrequency="2.0"
        app:waveHeight="50dp"
        app:waveInitialPhaseOffset="0"
        app:wavePhaseShift="-0.05"
        app:waveVerticalPosition="2" />
```

- waveAmount = number of waves
- waveColor = color of the wave
- waveFrequency = how frequent the waves are
- waveHeight - maximum height of center wave
- waveInitialPhaseOffset - if you would like to start wave in different position than default
- wavePhaseShift = how fast wave would be shifting towards end. 
     - negative value will shift wave to the right
     - position value will shift wave to the left
- waveVerticalPosition = basically where you want to set your wave on the screen. 2 will center the wave vertically on the screen
     - try value from 1 to 4

### public methods
* stopAnimation
* startAnimation
* setWaveColor(int waveColor)
* setStrokeWidth(float width)

--

Inspired by [SCSiriWaveformView](https://github.com/stefanceriu/SCSiriWaveformView). Thanks and much appreciation to your hard work. 

##License 
Copyright (C) 2016 Alex Gomes

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE X CONSORTIUM BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
