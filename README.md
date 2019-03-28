# SooRynPlayerProject

SooRynPlayer project source code

# Usage

```
allprojects {
    repositories {
       ...
       maven { url "https://jitpack.io" }

    }
}

dependencies {
    implementation 'com.github.kjjeon:SooRynPlayerProject:0.0.1'
}
```


# App Sample
```
  SooRynPlayer sooRynPlayer = new SooRynPlayer();
  sooRynPlayer.play(Context context, SurfaceHolder surfaceHolder, int rawResourceId);
```

