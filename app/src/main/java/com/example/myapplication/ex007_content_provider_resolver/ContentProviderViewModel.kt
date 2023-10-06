package com.example.myapplication.ex007_content_provider_resolver

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ProviderInfo
import android.database.Cursor
import android.net.Uri
import android.provider.Browser
import android.provider.UserDictionary
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import androidx.lifecycle.ViewModel


class ContentProviderViewModel: ViewModel() {

    private lateinit var contentResolver: ContentResolver
    private lateinit var packageManager: PackageManager

    private val target = "com.sec.android.app.sbrowser"
    fun fetchProvider(context: Context) {
        packageManager = context.packageManager
        contentResolver = context.contentResolver

        val pm = context.packageManager

//        pm.get

        val applicationInfoList = pm.getInstalledApplications(0)

//        applicationInfoList.forEach { applicationInfo ->
//            if (applicationInfo.packageName.contains(target))
//                retrieveApplicationInfo(applicationInfo)
//        }

//        val pm: PackageManager = activity.getPackageManager()
//        val pi: PackageInfo = pm.getPackageInfo(mpackagename.trim(), PackageManager.GET_META_DATA)
//        pm.getInstalledPackages(0).forEach { packageInfo ->
//            if (packageInfo.packageName.contains(target))
//                retrievePackageInfo(packageInfo)
//        }

//        val packageInfo = pm.getPackageInfo("com.sec.android.app.sbrowser", PackageManager.GET_META_DATA)
        contentResolverSearch(Uri.parse("content://browser/bookmarks"))

        contentResolverSearch(UserDictionary.Words.CONTENT_URI)
//        UserDictionary.CONTENT_URI

        listOf(
//            PackageManager.GET_ACTIVITIES,
//            PackageManager.GET_ATTRIBUTIONS_LONG,
//            PackageManager.GET_CONFIGURATIONS,
//            PackageManager.GET_GIDS,
//            PackageManager.GET_INSTRUMENTATION,
//            PackageManager.GET_META_DATA,
//            PackageManager.GET_PERMISSIONS,
            PackageManager.GET_PROVIDERS,
//            PackageManager.GET_RECEIVERS,
//            PackageManager.GET_RESOLVED_FILTER,
//            PackageManager.GET_SERVICES,
//            PackageManager.GET_SHARED_LIBRARY_FILES,
//            PackageManager.GET_SIGNING_CERTIFICATES,
//            PackageManager.GET_URI_PERMISSION_PATTERNS,
        ).forEach {
            try {
                retrievePackageInfo(pm.getPackageInfo("com.sec.android.app.sbrowser", it), it)
            } catch (e: Exception) {e.printStackTrace()}
//            pm?.getPackageInfo("com.sec.android.app.sbrowser", it)
        }

//        contentResolver(context.contentResolver)
    }

    private fun retrievePackageInfo(packageInfo: PackageInfo, flag: Int) {
        if (packageInfo.providers.isNullOrEmpty()) {
            Log.e("pi", "$packageInfo none providers ${getPackageManagerStringFlag(flag)}")
        } else {
            Log.w("pi", "$packageInfo some providers ${getPackageManagerStringFlag(flag)}")
            packageInfo.providers.forEach { retrievePackageInfopPoviders(it) }
        }
    }
    private fun retrievePackageInfopPoviders(pi: ProviderInfo) {
        Log.e("===", pi.toString() + //"providerInfoContent" +
        "\nauthority : " + pi.authority +
        "\nname : " + pi.name +
        "\npackageName : " + pi.packageName +
        "\nflags : " + pi.flags +
        "\napplicationInfo : " + pi.applicationInfo +
        "\ngrantUriPermissions : " + pi.grantUriPermissions +
        "\nreadPermission : " + pi.readPermission +
        "\ninitOrder : " + pi.initOrder +
        "\nmultiprocess : " + pi.multiprocess +
        "\nexported : " + pi.exported +
        "\nenabled : " + pi.enabled +
        "\nmetaData : " + pi.metaData +
        "\nprocessName : " + pi.processName
//        "\npathPermissions : " + pi.pathPermissions.map { it.path + it.readPermission + it.writePermission}.joinToString { "," } +
//        "\nforceUriPermissions : " + pi.forceUriPermissions +
//        "\nattributionTags : " + pi.attributionTags.joinToString { "," } +
//        "\nsplitName : " + pi.splitName +
        )

//        if (pi.name != "com.sec.android.app.sbrowser.sites.provider.SBrowserProvider") return

        if ( pi.authority == "com.sec.android.app.sbrowser.mostvisited" ) {
//        com.sec.android.app.sbrowser.mostvisited

            val splitedAuthority = pi.authority.split(";")

            splitedAuthority.forEach { authority ->
//            Browser.BOOKMARKS_URI
                listOf(
                    "most_visited_sites",
                    "pintab",
                    "SaferBrowsingDatabase",
                    "SBrowser",
                    "searchengine",
                    "si_log_database",

                    "mostVisitedSites",
                    "pintab",
                    "safer_browsing_database",
                    "s_browser",
                    "searchengine",
                    "siLogDatabase",

                    ).forEach { table ->
                    contentResolverSearch(Uri.parse("content://$authority/$table"))
                }
                contentResolverSearch(Uri.parse("content://$authority/${pi.name}"))
                contentResolverSearch(Uri.parse("content://$authority"))

//            contentResolverSearch(Uri.parse("content://${pi.name}"))
//            contentResolverSearch(Uri.parse("content://${pi.name}/$authority"))

                Log.w("---", "---------------------------------------------")
            }
        }
    }

//    private fun retrieveApplicationInfo(applicationInfo: ApplicationInfo) {
//        Log.e("ai", applicationInfo.toString())
////        applicationInfo.
////        applicationInfo.p
//    }

    private fun contentResolver() {
        val list = listOf(
            "com.sec.android.app.sbrowser.recentDownloadSearch",
            "com.sec.android.app.sbrowser.recentDownloadSearch",
            "com.sec.android.app.sbrowser.download.ui.recent_search.RecentDownloadSearchDbProvider",
            "com.sec.android.app.sbrowser.webPushNotification",
            "com.sec.android.app.sbrowser.webPushNotification",
            "com.sec.android.app.sbrowser.settings.notifications.NotificationProvider",
            "com.sec.android.app.sbrowser.notificationsearchkeywords",
            "com.sec.android.app.sbrowser.notificationsearchkeywords",
            "com.sec.android.app.sbrowser.settings.notifications.search.NotificationSearchKeywordProvider",
            "com.sec.android.app.sbrowser.searchindexables",
            "com.sec.android.app.sbrowser.searchindexables",
            "com.sec.android.app.sbrowser.settings.SbrowserSettingsSearchIndexablesProvider",
            "com.sec.android.app.sbrowser",
            "com.sec.android.app.sbrowser.browser",
            "com.sec.android.app.sbrowser.operatorbookmarks",
            "com.sec.android.app.sbrowser.ChromeBrowserProvider",
            "com.sec.android.app.sbrowser.BrowserProviderProxy",
            "com.sec.android.app.sbrowser",
            "com.sec.android.app.sbrowser.browser",
            "com.sec.android.app.sbrowser.operatorbookmarks",
            "com.sec.android.app.sbrowser.ChromeBrowserProvider",
            "com.sec.android.app.sbrowser.BrowserProviderProxy",
            "com.sec.android.app.sbrowser.sites.provider.SBrowserProvider",
            "com.sec.android.app.sbrowser.sbrowser2",
            "com.sec.android.app.sbrowser.sbrowser2",
            "com.sec.android.app.sbrowser.common.sdp.SBrowserInternalProvider",
            "com.sec.android.app.sbrowser.FileProvider",
            "com.sec.android.app.sbrowser.FileProvider",
            "androidx.core.content.FileProvider",
            "com.sec.android.app.sbrowser.DownloadFileProvider",
            "com.sec.android.app.sbrowser.DownloadFileProvider",
            "com.sec.android.app.sbrowser.common.download.provider.DownloadFileProvider",
            "com.sec.android.app.sbrowser.mediahistory",
            "com.sec.android.app.sbrowser.mediahistory",
            "com.sec.android.app.sbrowser.media.history.model.MHDatabaseProvider",
            "com.sec.android.app.sbrowser.quickaccesspinned",
            "com.sec.android.app.sbrowser.quickaccesspinned",
            "com.sec.android.app.sbrowser.quickaccess.db.QuickAccessProvider",
            "com.sec.android.app.sbrowser.searchengine",
            "com.sec.android.app.sbrowser.searchengine",
            "com.sec.android.app.sbrowser.searchengine.SearchEngineProvider",
            "com.sec.android.app.sbrowser.mostvisited",
            "com.sec.android.app.sbrowser.mostvisited",
            "com.sec.android.app.sbrowser.quickaccess.db.MostVisitedSitesProvider",
            "com.sec.android.app.sbrowser.sitessearchkeywords",
            "com.sec.android.app.sbrowser.sitessearchkeywords",
            "com.sec.android.app.sbrowser.sites.search.provider.SitesSearchKeywordProvider",
            "com.sec.android.app.sbrowser.scloud.quickaccess.sync",
            "com.sec.android.app.sbrowser.backup",
            "com.sec.android.app.sbrowser.scloud.sync",
            "com.sec.android.app.sbrowser.scloud.quickaccess.sync",
            "com.sec.android.app.sbrowser.backup;com.sec.android.app.sbrowser.scloud.sync",
            "com.samsung.android.scloud.oem.lib.ClientProvider",
            "com.sec.android.app.sbrowser.scloud.quickaccess.sync",
            "com.sec.android.app.sbrowser.backup",
            "com.sec.android.app.sbrowser.scloud.sync",
            "com.samsung.android.scloud.oem.lib.ClientProvider",
            "com.sec.android.app.sbrowser.scloud.quickaccess.sync",
            "com.sec.android.app.sbrowser.backup",
            "com.sec.android.app.sbrowser.scloud.sync",
            "com.samsung.android.scloud.oem.lib.ClientProvider",
            "com.sec.android.app.sbrowser.provider.DumpProvider",
            "com.sec.android.app.sbrowser.provider.DumpProvider",
            "com.sec.android.app.sbrowser.dump.provider.DumpProvider",
            "com.sec.android.app.sbrowser.account",
            "com.sec.android.app.sbrowser.account",
            "com.sec.android.app.sbrowser.sites.provider.AccountDataProvider",
            "com.sec.android.app.sbrowser.scloud.quickaccess.sync2",
            "com.sec.android.app.sbrowser.backup2",
            "com.sec.android.app.sbrowser.scloud.quickaccess.sync2",
            "com.sec.android.app.sbrowser.backup2",
            "com.sec.android.app.sbrowser.sync.v2.ClientProviderV2",
            "com.sec.android.app.sbrowser.scloud.quickaccess.sync2",
            "com.sec.android.app.sbrowser.backup2",
            "com.sec.android.app.sbrowser.sync.v2.ClientProviderV2",
            "com.sec.android.app.sbrowser.handoffData",
            "com.sec.android.app.sbrowser.handoffData",
            "com.sec.android.app.sbrowser.handoff.HandoffDataProvider",
            "com.sec.android.app.sbrowser.androidx-startup",
            "com.sec.android.app.sbrowser.androidx-startup",
            "androidx.startup.InitializationProvider",
            "com.sec.android.app.sbrowser.CapsuleProvider",
            "com.sec.android.app.sbrowser.CapsuleProvider",
            "com.samsung.android.sdk.bixby2.provider.CapsuleProvider",
        ).toSet().toList()

        list.forEach { Log.e("---", it) }

        list.forEach {
            val uri = Uri.parse("content://$it")
            contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or
//                     Intent.ACTION_GET_CONTENT or
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            contentResolverSearch(Uri.parse("content://$it"))
        }
    }

    private fun contentResolverSearch(uri: Uri) {
        try {
//            val cursor: Cursor? = contentResolver.query(uri, arrayOf<String>(), null, arrayOf<String>(), null)
            val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

            if (null == cursor) {
                Log.e("cr", "$uri cursor null")
                return
            }
            Log.e("cr", "$uri cursor not null")

            cursor.moveToFirst()

            while (cursor.moveToNext()) {
                for (i in 0 until  cursor.columnCount) {
                    Log.w("cr", "$uri cursor $cursor $i, cName:${cursor.getColumnName(i)}, cVal:${cursor.getString(i)}")
                }
            }

            cursor.close()
        } catch (e: Exception) {
            Log.w("cr", "$uri cursor error")
            e.printStackTrace()
        }

        var accessibilityNodeInfo: AccessibilityNodeInfo
//        accessibilityNodeInfo.performAction()
    }


    fun getPackageManagerStringFlag(v: Int): String {
        return when (v) {
            PackageManager.GET_ACTIVITIES -> "PackageManager.GET_ACTIVITIES"
            PackageManager.GET_CONFIGURATIONS -> "PackageManager.GET_CONFIGURATIONS"
            PackageManager.GET_GIDS -> "PackageManager.GET_GIDS"
            PackageManager.GET_INSTRUMENTATION -> "PackageManager.GET_INSTRUMENTATION"
            PackageManager.GET_META_DATA -> "PackageManager.GET_META_DATA"
            PackageManager.GET_PERMISSIONS -> "PackageManager.GET_PERMISSIONS"
            PackageManager.GET_PROVIDERS -> "PackageManager.GET_PROVIDERS"
            PackageManager.GET_RECEIVERS -> "PackageManager.GET_RECEIVERS"
            PackageManager.GET_RESOLVED_FILTER -> "PackageManager.GET_RESOLVED_FILTER"
            PackageManager.GET_SERVICES -> "PackageManager.GET_SERVICES"
            PackageManager.GET_SHARED_LIBRARY_FILES -> "PackageManager.GET_SHARED_LIBRARY_FILES"
            PackageManager.GET_SIGNING_CERTIFICATES -> "PackageManager.GET_SIGNING_CERTIFICATES"
            PackageManager.GET_URI_PERMISSION_PATTERNS -> "PackageManager.GET_URI_PERMISSION_PATTERNS"
            else -> "PackageManager unknown"
        }
    }


}