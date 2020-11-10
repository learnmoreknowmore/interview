package com.decay.jetpack.dataStore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow

/**
 * https://juejin.im/post/6881442312560803853
 *
 * Jetpack DataStore 是经过改进的新版数据存储解决方案，旨在取代 SharedPreferences，让您的应用能够以异步、一致的事务方式存储数据
 */
class DataStoreUtils {
    /**
     * Proto DataStore：
     * 存储类的对象（typed objects ），通过 protocol buffers 将对象序列化存储在本地，protocol buffers 现在已经应用的非常广泛，无论是微信还是阿里等等大厂都在使用，
     * 我们在部分业务场景中也用到了 protocol buffers，会在后续的文章详细分析
     * Preferences DataStore：
     * 以键值对的形式存储在本地和 SharedPreferences 类似，
     * 但是 DataStore 是基于 Flow 实现的，不会阻塞主线程，并且保证类型安全
     *
     */
    //Use the Context.createDataStore() extension function to create an instance of DataStore<Preferences>. The mandatory name parameter is the name of the Preferences DataStore.
//    fun createPreferenceDataStore(context:Context){
//        val dataStore: DataStore<Preferences> = context.createDataStore(fileName = "settings")
//    }
    //Read from a Preferences DataStore
    fun readPreferenceDataStore(){
//        val EXAMPLE_COUNTER = preferencesKey<Int>("example_counter")
//        val exampleCounterFlow: Flow<Int> = dataStore.data
//            .map { preferences ->
//                // No type safety.
//                preferences[EXAMPLE_COUNTER] ?: 0
//            }
    }

}