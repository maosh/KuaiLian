//package com.maosh.kuailian;
//
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.app.AlertDialog.Builder;
//import android.app.ProgressDialog;
//import android.content.ActivityNotFoundException;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.TextUtils;
//import android.util.DisplayMetrics;
//import android.view.ContextMenu;
//import android.view.ContextMenu.ContextMenuInfo;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.WindowManager;
//import android.widget.AdapterView.AdapterContextMenuInfo;
//import android.widget.CheckBox;
//import android.widget.ListView;
//import android.widget.TextView;
//import com.actionbarsherlock.view.Menu;
//import com.easyovpn.easyovpn.EasyApp;
//import com.easyovpn.easyovpn.b.d;
//import com.easyovpn.easyovpn.b.h;
//import com.easyovpn.easyovpn.b.j;
//import com.easyovpn.easyovpn.core.RpcService;
//import com.easyovpn.easyovpn.model.m;
//import com.easyovpn.easyovpn.model.p;
//import com.easyovpn.easyovpn.ui.a.i;
//import java.io.File;
//
//public class ServerListActivity
//  extends i
//{
//  private static final String d = ServerListActivity.class.getSimpleName();
//  private com.easyovpn.easyovpn.b.a e;
//  private ProgressDialog f;
//  private TextView g;
//  private ListView h;
//  private com.actionbarsherlock.view.MenuItem i;
//  private com.actionbarsherlock.view.MenuItem j;
//  private String k;
//  private int l = 0;
//  private long m;
//  private Handler n = new Handler();
//  private BroadcastReceiver o = new q(this);
//  @SuppressLint({"NewApi"})
//  private View.OnClickListener p = new s(this);
//  
//  /* Error */
//  private String a(long paramLong)
//  {
//    // Byte code:
//    //   0: aconst_null
//    //   1: astore_3
//    //   2: aload_0
//    //   3: invokevirtual 73	com/easyovpn/easyovpn/ui/ServerListActivity:getContentResolver	()Landroid/content/ContentResolver;
//    //   6: lload_1
//    //   7: invokestatic 78	com/easyovpn/easyovpn/model/m:a	(J)Landroid/net/Uri;
//    //   10: getstatic 83	com/easyovpn/easyovpn/core/a/b:a	[Ljava/lang/String;
//    //   13: aconst_null
//    //   14: aconst_null
//    //   15: aconst_null
//    //   16: invokevirtual 89	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
//    //   19: astore 4
//    //   21: aload 4
//    //   23: ifnull +82 -> 105
//    //   26: aload 4
//    //   28: invokeinterface 95 1 0
//    //   33: ifeq +159 -> 192
//    //   36: new 80	com/easyovpn/easyovpn/core/a/b
//    //   39: dup
//    //   40: aload 4
//    //   42: iconst_1
//    //   43: invokeinterface 99 2 0
//    //   48: aload 4
//    //   50: iconst_2
//    //   51: invokeinterface 99 2 0
//    //   56: aload 4
//    //   58: iconst_3
//    //   59: invokeinterface 103 2 0
//    //   64: aload 4
//    //   66: iconst_4
//    //   67: invokeinterface 103 2 0
//    //   72: aload 4
//    //   74: iconst_5
//    //   75: invokeinterface 103 2 0
//    //   80: aconst_null
//    //   81: invokespecial 106	com/easyovpn/easyovpn/core/a/b:<init>	(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;)V
//    //   84: astore_3
//    //   85: aload_3
//    //   86: aload 4
//    //   88: bipush 7
//    //   90: invokeinterface 99 2 0
//    //   95: putfield 108	com/easyovpn/easyovpn/core/a/b:j	Ljava/lang/String;
//    //   98: aload 4
//    //   100: invokeinterface 111 1 0
//    //   105: aload_3
//    //   106: ifnull +84 -> 190
//    //   109: getstatic 117	java/util/Locale:US	Ljava/util/Locale;
//    //   112: ldc 119
//    //   114: iconst_4
//    //   115: anewarray 121	java/lang/Object
//    //   118: dup
//    //   119: iconst_0
//    //   120: aload_0
//    //   121: invokestatic 127	com/easyovpn/easyovpn/b/j:c	(Landroid/content/Context;)Ljava/lang/String;
//    //   124: aastore
//    //   125: dup
//    //   126: iconst_1
//    //   127: aload_3
//    //   128: getfield 128	com/easyovpn/easyovpn/core/a/b:d	Ljava/lang/String;
//    //   131: aastore
//    //   132: dup
//    //   133: iconst_2
//    //   134: aload_3
//    //   135: getfield 130	com/easyovpn/easyovpn/core/a/b:e	Ljava/lang/String;
//    //   138: aastore
//    //   139: dup
//    //   140: iconst_3
//    //   141: aload_3
//    //   142: getfield 132	com/easyovpn/easyovpn/core/a/b:f	I
//    //   145: invokestatic 138	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
//    //   148: aastore
//    //   149: invokestatic 144	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
//    //   152: astore 4
//    //   154: new 146	java/io/File
//    //   157: dup
//    //   158: aload 4
//    //   160: invokespecial 149	java/io/File:<init>	(Ljava/lang/String;)V
//    //   163: aload_3
//    //   164: getfield 108	com/easyovpn/easyovpn/core/a/b:j	Ljava/lang/String;
//    //   167: ldc -105
//    //   169: invokestatic 156	co/easy4u/a/b:a	(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V
//    //   172: aload 4
//    //   174: areturn
//    //   175: astore_3
//    //   176: aload 4
//    //   178: invokeinterface 111 1 0
//    //   183: aload_3
//    //   184: athrow
//    //   185: astore_3
//    //   186: aload_3
//    //   187: invokevirtual 159	java/io/IOException:printStackTrace	()V
//    //   190: aconst_null
//    //   191: areturn
//    //   192: aconst_null
//    //   193: astore_3
//    //   194: goto -96 -> 98
//    // Local variable table:
//    //   start	length	slot	name	signature
//    //   0	197	0	this	ServerListActivity
//    //   0	197	1	paramLong	long
//    //   1	163	3	localb	com.easyovpn.easyovpn.core.a.b
//    //   175	9	3	localObject1	Object
//    //   185	2	3	localIOException	java.io.IOException
//    //   193	1	3	localObject2	Object
//    //   19	158	4	localObject3	Object
//    // Exception table:
//    //   from	to	target	type
//    //   26	98	175	finally
//    //   154	172	185	java/io/IOException
//  }
//  
//  private void a(Context paramContext, long paramLong)
//  {
//    new u(this, paramContext, paramLong).execute(new Void[0]);
//  }
//  
//  private void a(String paramString)
//  {
//    try
//    {
//      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
//      return;
//    }
//    catch (Exception paramString) {}
//  }
//  
//  private boolean a(long paramLong1, long paramLong2)
//  {
//    if (paramLong1 == 2131492997L)
//    {
//      a(this, paramLong2);
//      return true;
//    }
//    return false;
//  }
//  
//  private void b(String paramString)
//  {
//    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//    if (TextUtils.equals(paramString, "de.schaeuffelhut.android.openvpn")) {
//      localBuilder.setTitle(2131230814).setMessage(2131230818);
//    }
//    for (;;)
//    {
//      localBuilder.setPositiveButton(17039370, new x(this, paramString));
//      localBuilder.setNegativeButton(17039360, null);
//      localBuilder.show();
//      return;
//      if (TextUtils.equals(paramString, "net.openvpn.openvpn")) {
//        localBuilder.setTitle(2131230814).setMessage(2131230817);
//      } else {
//        localBuilder.setTitle(2131230814).setMessage(2131230819);
//      }
//    }
//  }
//  
//  private void c(String paramString)
//  {
//    if ((p.d()) && (this.e != null) && (!this.e.f()) && (this.e.g())) {
//      this.e.a(new z(this, paramString));
//    }
//    for (;;)
//    {
//      return;
//      if (EasyApp.b()) {
//        try
//        {
//          co.easy4u.a.b.a(new File(paramString), new File(com.easyovpn.easyovpn.a.a));
//          if (!m())
//          {
//            b("de.schaeuffelhut.android.openvpn");
//            return;
//          }
//        }
//        catch (Exception paramString)
//        {
//          for (;;)
//          {
//            com.easyovpn.easyovpn.c.a(d, paramString.toString());
//            paramString.printStackTrace();
//          }
//        }
//      }
//    }
//    Intent localIntent = new Intent("android.intent.action.VIEW");
//    localIntent.addCategory("android.intent.category.DEFAULT");
//    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/x-openvpn-profile");
//    if (localIntent.resolveActivity(getPackageManager()) == null)
//    {
//      l();
//      return;
//    }
//    try
//    {
//      startActivityForResult(localIntent, 10002);
//      p.a(true);
//      return;
//    }
//    catch (ActivityNotFoundException paramString)
//    {
//      com.easyovpn.easyovpn.c.a(d, "No Application can handle the ovpn file.");
//    }
//  }
//  
//  private boolean h()
//  {
//    if ((Build.VERSION.SDK_INT < 14) && (!h.a()))
//    {
//      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//      localBuilder.setTitle(2131230759).setMessage(2131230857);
//      localBuilder.setPositiveButton(2131230858, new v(this));
//      localBuilder.show();
//      return true;
//    }
//    return false;
//  }
//  
//  private void i()
//  {
//    if ((this.e != null) && (!this.e.f())) {
//      this.e.a(null);
//    }
//  }
//  
//  private void j()
//  {
//    if (EasyApp.b()) {
//      if (!m()) {
//        b("de.schaeuffelhut.android.openvpn");
//      }
//    }
//    do
//    {
//      return;
//      if (p.m())
//      {
//        k();
//        return;
//      }
//    } while (n());
//    l();
//  }
//  
//  private void k()
//  {
//    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//    View localView = LayoutInflater.from(this).inflate(2130903067, null);
//    CheckBox localCheckBox = (CheckBox)localView.findViewById(2131492948);
//    localBuilder.setView(localView).setTitle(2131230815);
//    localBuilder.setMessage(2131230816);
//    localBuilder.setPositiveButton(17039370, new w(this, localCheckBox));
//    localBuilder.show();
//  }
//  
//  private void l()
//  {
//    if (j.a())
//    {
//      b("net.openvpn.openvpn");
//      return;
//    }
//    b("de.blinkt.openvpn");
//  }
//  
//  private boolean m()
//  {
//    if (!co.easy4u.a.a.a(this, "de.schaeuffelhut.android.openvpn")) {
//      return false;
//    }
//    try
//    {
//      Intent localIntent = getPackageManager().getLaunchIntentForPackage("de.schaeuffelhut.android.openvpn");
//      localIntent.addCategory("android.intent.category.LAUNCHER");
//      startActivityForResult(localIntent, 10002);
//      p.a(true);
//      return true;
//    }
//    catch (ActivityNotFoundException localActivityNotFoundException)
//    {
//      com.easyovpn.easyovpn.c.a(d, localActivityNotFoundException.toString());
//    }
//    return false;
//  }
//  
//  @TargetApi(9)
//  private boolean n()
//  {
//    String str = null;
//    boolean bool = false;
//    if (co.easy4u.a.a.a(this, "net.openvpn.openvpn")) {
//      str = "net.openvpn.openvpn";
//    }
//    for (;;)
//    {
//      if (str != null) {}
//      try
//      {
//        startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", str, null)));
//        bool = true;
//        return bool;
//      }
//      catch (ActivityNotFoundException localActivityNotFoundException)
//      {
//        com.easyovpn.easyovpn.c.a(d, localActivityNotFoundException.toString());
//      }
//      if (co.easy4u.a.a.a(this, "de.blinkt.openvpn")) {
//        str = "de.blinkt.openvpn";
//      }
//    }
//    return false;
//  }
//  
//  private void o()
//  {
//    if (!RpcService.a())
//    {
//      RpcService.a(getApplicationContext(), this.k);
//      q();
//    }
//  }
//  
//  private void p()
//  {
//    if (this.j != null)
//    {
//      if (!TextUtils.isEmpty(this.k)) {
//        break label61;
//      }
//      this.j.setIcon(2130837657);
//    }
//    while ((this.i != null) && (this.i.getActionView() == null))
//    {
//      this.j.setEnabled(true);
//      return;
//      label61:
//      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
//      Bitmap localBitmap = d.a(this, this.k);
//      this.j.setIcon(d.a(getResources(), localBitmap, localDisplayMetrics.density));
//    }
//    this.j.setEnabled(false);
//  }
//  
//  private void q()
//  {
//    r();
//    this.g.setText(p.g());
//    if ((this.i != null) && (!RpcService.a()) && (this.e != null)) {
//      this.e.d();
//    }
//  }
//  
//  private void r()
//  {
//    if (this.i != null)
//    {
//      if (!RpcService.a()) {
//        break label43;
//      }
//      if (this.i.getActionView() == null) {
//        this.i.setActionView(2130903071);
//      }
//    }
//    for (;;)
//    {
//      p();
//      return;
//      label43:
//      this.i.setActionView(null);
//    }
//  }
//  
//  protected com.easyovpn.easyovpn.ui.a.c a()
//  {
//    return new ab(this, this, null);
//  }
//  
//  protected ListView b()
//  {
//    this.h = ((ListView)findViewById(16908298));
//    this.h.setAdapter(this.b);
//    this.h.setChoiceMode(1);
//    this.h.setOnItemClickListener(new y(this));
//    if ((Build.VERSION.SDK_INT < 11) && (p.a(this))) {
//      this.h.setOnCreateContextMenuListener(this);
//    }
//    return this.h;
//  }
//  
//  protected void c()
//  {
//    if (!TextUtils.isEmpty(this.k)) {}
//    for (String str = String.format("%s='%s'", new Object[] { "country", this.k });; str = null)
//    {
//      super.a(m.a(), com.easyovpn.easyovpn.core.a.b.b, str, null, "_id ASC");
//      return;
//    }
//  }
//  
//  protected void d()
//  {
//    this.n.postDelayed(new aa(this), 1000L);
//  }
//  
//  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
//  {
//    if (paramInt1 == 10001) {
//      if (paramInt2 == -1)
//      {
//        paramIntent = com.easyovpn.easyovpn.model.a.a(this);
//        if (!TextUtils.equals(paramIntent, this.k))
//        {
//          this.k = paramIntent;
//          o();
//          c();
//        }
//        this.l += 1;
//        if (this.l % com.easyovpn.easyovpn.model.n.e() == 0) {
//          i();
//        }
//      }
//    }
//    do
//    {
//      return;
//      if (paramInt1 != 10002) {
//        break;
//      }
//    } while (!j.b());
//    i();
//    co.easy4u.a.b.a.b(this, false);
//    return;
//    super.onActivityResult(paramInt1, paramInt2, paramIntent);
//  }
//  
//  public boolean onContextItemSelected(android.view.MenuItem paramMenuItem)
//  {
//    AdapterView.AdapterContextMenuInfo localAdapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo();
//    if ((localAdapterContextMenuInfo != null) && (a(paramMenuItem.getItemId(), localAdapterContextMenuInfo.id))) {
//      return true;
//    }
//    return super.onContextItemSelected(paramMenuItem);
//  }
//  
//  public void onCreate(Bundle paramBundle)
//  {
//    super.onCreate(paramBundle);
//    setContentView(2130903072);
//    this.g = ((TextView)findViewById(2131492943));
//    if (!p.a(this))
//    {
//      this.e = new com.easyovpn.easyovpn.b.a(this);
//      this.e.d();
//      this.e.e();
//    }
//    b();
//    if ((co.easy4u.a.a.a(this)) && (p.e())) {
//      o();
//    }
//    this.k = com.easyovpn.easyovpn.model.a.a(this);
//    paramBundle = new IntentFilter("com.easyovpn.easyovpn.action_update_modified");
//    android.support.v4.a.n.a(this).a(this.o, paramBundle);
//    if (!h()) {
//      EasyApp.a(this);
//    }
//  }
//  
//  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
//  {
//    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
//    try
//    {
//      getMenuInflater().inflate(2131623938, paramContextMenu);
//      return;
//    }
//    catch (Exception paramContextMenu) {}
//  }
//  
//  public boolean onCreateOptionsMenu(Menu paramMenu)
//  {
//    getSupportMenuInflater().inflate(2131623937, paramMenu);
//    this.i = paramMenu.findItem(2131492985);
//    this.j = paramMenu.findItem(2131492987);
//    if (p.a(this))
//    {
//      paramMenu.findItem(2131492990).setVisible(false);
//      paramMenu.findItem(2131492992).setVisible(true);
//    }
//    for (;;)
//    {
//      r();
//      return super.onCreateOptionsMenu(paramMenu);
//      paramMenu.findItem(2131492990).setVisible(true);
//      paramMenu.findItem(2131492992).setVisible(false);
//    }
//  }
//  
//  protected void onDestroy()
//  {
//    if (this.e != null) {
//      this.e.a();
//    }
//    p.a();
//    android.support.v4.a.n.a(this).a(this.o);
//    super.onDestroy();
//  }
//  
//  public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem paramMenuItem)
//  {
//    switch (paramMenuItem.getItemId())
//    {
//    case 2131492989: 
//    default: 
//    case 2131492985: 
//    case 2131492986: 
//    case 2131492988: 
//    case 2131492995: 
//    case 2131492996: 
//    case 2131492994: 
//    case 2131492993: 
//    case 2131492992: 
//    case 2131492991: 
//      for (;;)
//      {
//        return super.onOptionsItemSelected(paramMenuItem);
//        o();
//        continue;
//        j();
//        com.umeng.a.a.a(this, "ovpn_ovpn");
//        continue;
//        co.easy4u.toolbox.b.a.b(this);
//        continue;
//        startActivity(new Intent(this, AboutActivity.class));
//        continue;
//        co.easy4u.toolbox.a.c(this);
//        com.umeng.a.a.a(this, "ovpn_help");
//        continue;
//        co.easy4u.toolbox.b.a.a(this);
//        continue;
//        startActivity(new Intent(this, SettingsActivity.class));
//        continue;
//        startActivity(new Intent(this, OvpnShareActivity.class));
//        continue;
//        co.easy4u.toolbox.a.b(this);
//        com.umeng.a.a.a(this, "ovpn_apps");
//      }
//    case 2131492987: 
//      startActivityForResult(new Intent(this, CountryListActivity.class), 10001);
//      return true;
//    }
//    startActivity(new Intent(this, BuyProActivity.class));
//    return true;
//  }
//  
//  protected void onPause()
//  {
//    if (this.e != null) {
//      this.e.b();
//    }
//    super.onPause();
//    com.umeng.a.a.a(this);
//  }
//  
//  protected void onResume()
//  {
//    super.onResume();
//    com.umeng.a.a.b(this);
//    if (this.e != null) {
//      this.e.c();
//    }
//    q();
//  }
//}
