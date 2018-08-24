# NavigationDrawerWithActionbar

NavigationDrawer with ActionBar

### Support Library Dependencies

```
implementation 'com.android.support:design:28.0.0-rc01'
```

### AndroidManifest.xml

```xml
android:theme="@style/Theme.AppCompat.Light.NoActionBar"
```

### activity_main.xml

**Add NavigationView inside DrawerLayout**
**FrameLayout below will display as main body of the screen**
**Toolbar inside FrameLayout will ActionBar in top of screen**

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/my_drawer"
    tools:context=".MainActivity">

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.v7.widget.Toolbar
            android:id="@+id/my_toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            android:elevation="4dp"
            android:theme="@style/ThemeOverlay.AppCompat.ActionBar"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>

        <TextView
            android:id="@+id/textView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:textSize="30sp"/>
    </FrameLayout>

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/my_header"
        app:menu="@menu/my_nav_menu" />

</android.support.v4.widget.DrawerLayout>
```
### Sample Screenshot with Actionbar

<img src="https://image.ibb.co/cdJMXp/Screenshot_1535133672.png" alt="NavigationDrawer_Screenshot" width="300"/>

### Sample Screenshot with both Menu & Header 

<img src="https://image.ibb.co/ngwmyU/Screenshot_1535133675.png" alt="NavigationDrawer_Screenshot" width="300"/>


### Adding Menu Items for NavigationDrawer under res/layout/my_nav_menu.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <group android:checkableBehavior="single"
        android:id="@+id/group1">
        <item
            android:id="@+id/nav_account"
            android:icon="@drawable/account"
            android:title="Account" />
        <item
            android:id="@+id/nav_gallery"
            android:icon="@drawable/gallery"
            android:title="Gallery" />
    </group>
    <group android:checkableBehavior="single"
        android:id="@+id/group2">
        <item
            android:id="@+id/nav_slideshow"
            android:icon="@drawable/settings"
            android:title="Settings" />
    </group>
</menu>
```

### my_app_menu.xml for displaying menu in ActionBar

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item android:id="@+id/item1"
        android:icon="@drawable/camera"
        android:title="Open Camera"
        app:showAsAction="ifRoom"/>
    <item app:showAsAction="never"
        android:title="Settings"
        android:id="@+id/item2"
        />
</menu>
```

### Adding Header for NavigationDrawer under res/menu/my_header.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="120dp"
    android:background="@android:color/black">

    <ImageView
        android:layout_width="80dp"
        android:layout_height="100dp"
        android:layout_centerInParent="true"
        android:background="@android:color/holo_red_dark"
        android:src="@drawable/iron"/>

</RelativeLayout>
```

### MainActivity.java

```java
public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    TextView textView;
    DrawerLayout drawerLayout;
    String welcomeText = "Hello, India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            welcomeText = savedInstanceState.getString("t1");
        }

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menubar);

        drawerLayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        textView.setText(welcomeText);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_account:
                        textView.setText(menuItem.getTitle());
                        break;
                    case R.id.nav_gallery:
                        textView.setText(menuItem.getTitle());
                        break;
                    case R.id.nav_slideshow:
                        textView.setText(menuItem.getTitle());
                        break;
                }
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("t1", textView.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
```

Reference : [Implementing Navigation Drawer](https://developer.android.com/training/implementing-navigation/nav-drawer)

