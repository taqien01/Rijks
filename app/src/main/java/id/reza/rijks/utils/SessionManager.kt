package id.reza.rijks.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import id.reza.rijks.model.DataUser
import id.reza.rijks.model.SatuUser
import id.reza.rijks.model.User

class SessionManager(context: Context) {

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(Constant.SESSION_NAME, 0)
        editor = pref.edit()
    }

    var isLogged: Boolean
        get() = pref.getBoolean(Constant.ACCOUNT_ISLOGGED, false)
        set(logged) {
            editor.putBoolean(Constant.ACCOUNT_ISLOGGED, logged)
            editor.commit()
        }

    var hasUser: Boolean
        get() = pref.getBoolean(Constant.ACCOUNT_HASUSER, false)
        set(user) {
            editor.putBoolean(Constant.ACCOUNT_HASUSER, user)
            editor.commit()
        }

    var userAccountRegister: User
        get() {
            val gson = Gson()
            val json: String = pref.getString(Constant.ACCOUNT_REGISTER, "")!!
            return gson.fromJson(json, User::class.java)
        }
        set(info) {
            val gson = Gson()
            val json: String = gson.toJson(info)
            editor.putString(Constant.ACCOUNT_REGISTER, json)
            editor.commit()
        }

    var userAccountInfo: DataUser
        get() {
            val gson = Gson()
            val json: String = pref.getString(Constant.ACCOUNT_INFO, "")!!
            return gson.fromJson(json, DataUser::class.java)
        }
        set(info) {
            val gson = Gson()
            val json: String = gson.toJson(info)
            editor.putString(Constant.ACCOUNT_INFO, json)
            editor.commit()
        }

    fun resetSession() {
//        editor.clear()
        editor.remove(Constant.ACCOUNT_ISLOGGED).commit()
        editor.remove(Constant.ACCOUNT_INFO).commit()
    }
}