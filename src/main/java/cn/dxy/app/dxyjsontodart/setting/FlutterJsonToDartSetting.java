package cn.dxy.app.dxyjsontodart.setting;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(name = "FlutterJsonToDart", storages = {@Storage(value = "FlutterJsonToDart.xml")})
public class FlutterJsonToDartSetting implements PersistentStateComponent<FlutterJsonToDartSetting> {

    public boolean createToJson = true;
    public boolean defaultValue = false;


    public static FlutterJsonToDartSetting getInstance() {
        return ApplicationManager.getApplication().getService(FlutterJsonToDartSetting.class);
    }


    @Override
    public @Nullable FlutterJsonToDartSetting getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull FlutterJsonToDartSetting state) {
        this.createToJson = state.createToJson;
        this.defaultValue = state.defaultValue;
    }


}