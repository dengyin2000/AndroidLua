package com.example.androidlua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaUtil {

    private static final String TAG = "LuaUtil";
    Globals globals = JsePlatform.standardGlobals();

    public void compile(String codeString) {
            globals.load(codeString).call();
    }

    public Object invoke(String func, String param) {
        return globals.get(func).call(LuaValue.valueOf(param));
    }

    public Object invoke(String func, Object... parameters) {
        if (parameters != null && parameters.length > 0) {
            LuaValue[] values = new LuaValue[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                values[i] = CoerceJavaToLua.coerce(parameters[i]);
            }
            return globals.get(func).call(LuaValue.listOf(values));
        } else {
            return globals.get(func).call();
        }
    }

}
