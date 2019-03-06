# SooRinPlayerProject

SooRinPlayer project source code 

# Usage

```
allprojects {
    repositories {
       ...
       maven { url "https://jitpack.io" }

    }
}

dependencies {
    implementation 'com.github.kjjeon:SooRinPlayerProject:0.0.1'
}
```


# App Sample
```
  SooRinPlayer sooRinPlayer = new SooRinPlayer();
  sooRinPlayer.play(Context context, SurfaceHolder surfaceHolder, int rawResourceId);
```

