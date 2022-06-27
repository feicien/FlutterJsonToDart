package cn.dxy.app.dxyjsontodart;

public class FlutterDependencyBean {

    public String packageName;
    public String currentVersion;
    public int index;

    public FlutterDependencyBean(String packageName, String currentVersion, int index) {
        this.packageName = packageName;
        this.currentVersion = currentVersion;
        this.index = index;
    }


    @Override
    public String toString() {
        return "FlutterDependencyBean{" +
                "packageName='" + packageName + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", index=" + index +
                '}';
    }
}
