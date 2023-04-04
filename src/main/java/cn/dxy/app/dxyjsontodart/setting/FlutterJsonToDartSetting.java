package cn.dxy.app.dxyjsontodart.setting;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(name = "FlutterJsonToDart", storages = {@Storage(value = "FlutterJsonToDart.xml")})
public class FlutterJsonToDartSetting implements PersistentStateComponent<FlutterJsonToDartSetting> {

    //是否生成 toJson 方法
    public boolean createToJson = true;
    //是否对生成的 model 字段，设置默认值
    public boolean defaultValue = false;
    //是否设置 JsonKey.name
    public boolean useJsonKeyName = true;
    // 是否设置 explicitToJson
    public boolean explicitToJson = false;
    //是否设置 fieldRename
    public boolean fieldRename = false;
    //是否生成 copyWith
    public boolean copyWith = false;


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
        this.useJsonKeyName = state.useJsonKeyName;
        this.explicitToJson = state.explicitToJson;
        this.fieldRename = state.fieldRename;
        this.copyWith = state.copyWith;
    }


}