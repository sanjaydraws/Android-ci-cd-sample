package com.sanjayprajapat.androidcicd

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.databinding.ktx.BuildConfig
import com.sanjayprajapat.androidcicd.BuildConfig.BASE_URL
import com.sanjayprajapat.androidcicd.BuildConfig.FLAVOR
import com.sanjayprajapat.androidcicd.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.apply {
            setContentView(root)
        }
//        Log.d("TAG", "onCreate: ${BuildConfig.BASE_URL}")
//        if(BuildConfig.DEBUG){
//
//            if (BuildConfig.IS_LIVE.equals("debug")) {
//                binding?.myBuild?.text = "this is staging debug Build"
//            }else if(BuildConfig.IS_LIVE.equals("release")){
//                binding?.myBuild?.text = "this is staging release Build"
//            }
//        }else{
//            if (BuildConfig.IS_LIVE.equals("debug")) {
//                binding?.myBuild?.text = "this is live debug Build"
//            }else if(BuildConfig.IS_LIVE.equals("release")){
//                binding?.myBuild?.text = "this is live release Build"
//            }
//        }
        printHashKey()
    }

    fun printHashKey(){
        try {
            val info = packageManager?.getPackageInfo("com.sanjayprajapat.androidcicd", PackageManager.GET_SIGNATURES)
            info?.signatures?.let {
                for (signature in info?.signatures) {
                    var md: MessageDigest
                    md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val something: String = String(Base64.encode(md.digest(), 0))
                    //String something = new String(Base64.encodeBytes(md.digest()));
                    Log.e("hash key", something) //cSt5ojLmDGOoTFyVnckUSqLOef0=
                }
            }
        } catch (e1: PackageManager.NameNotFoundException) {
            Log.e("name not found", e1.toString())
        } catch (e: NoSuchAlgorithmException) {
            Log.e("no such an algorithm", e.toString())
        } catch (e: java.lang.Exception) {
            Log.e("exception", e.toString())
        }
    }
}