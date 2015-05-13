//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.rzo.yajsw.config;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileOptionsProvider;
import org.apache.commons.configuration.FileSystem;
import org.apache.commons.configuration.Interpolator;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.VFSFileSystem;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.rzo.yajsw.config.YajswConfiguration;
import org.rzo.yajsw.config.jnlp.JnlpSupport;
import org.rzo.yajsw.os.OperatingSystem;
import org.rzo.yajsw.script.GroovyScript;
import org.rzo.yajsw.util.CaseInsensitiveMap;
import org.rzo.yajsw.util.CommonsLoggingAdapter;
import org.rzo.yajsw.util.VFSUtils;
import org.rzo.yajsw.wrapper.WrappedProcess;

public class YajswConfigurationImpl extends CompositeConfiguration implements YajswConfiguration {
    InternalLogger log;
    Configuration _systemProperties;
    Configuration _localConfiguration;
    CompositeConfiguration _systemConfiguration;
    boolean debug;
    boolean _useSystemProperties;
    boolean _isStopper;
    boolean _init;
    PropertiesConfiguration _fileConfiguration;
    Interpolator _interpolator;
    Set _interpolated;
    Map _scriptUtils;
    boolean _isJavaDebug;

    public YajswConfigurationImpl() {
        this.log = InternalLoggerFactory.getInstance(this.getClass().getName());
        this._systemConfiguration = new CompositeConfiguration();
        this.debug = false;
        this._useSystemProperties = true;
        this._isStopper = false;
        this._init = false;
        this._fileConfiguration = null;
        this._interpolated = new HashSet();
        this._scriptUtils = null;
        this._isJavaDebug = false;
        this.init();
    }

    public YajswConfigurationImpl(boolean debug) {
        this.log = InternalLoggerFactory.getInstance(this.getClass().getName());
        this._systemConfiguration = new CompositeConfiguration();
        this.debug = false;
        this._useSystemProperties = true;
        this._isStopper = false;
        this._init = false;
        this._fileConfiguration = null;
        this._interpolated = new HashSet();
        this._scriptUtils = null;
        this._isJavaDebug = false;
        this.setDebug(debug);
        this.init();
    }

    public YajswConfigurationImpl(Configuration localConfiguration, boolean useSystemProperties) {
        this(localConfiguration, useSystemProperties, (Map)null);
    }

    public YajswConfigurationImpl(Configuration localConfiguration, boolean useSystemProperties, Map scriptUtils) {
        this.log = InternalLoggerFactory.getInstance(this.getClass().getName());
        this._systemConfiguration = new CompositeConfiguration();
        this.debug = false;
        this._useSystemProperties = true;
        this._isStopper = false;
        this._init = false;
        this._fileConfiguration = null;
        this._interpolated = new HashSet();
        this._scriptUtils = null;
        this._isJavaDebug = false;
        this._localConfiguration = localConfiguration;
        this._useSystemProperties = useSystemProperties;
        this._scriptUtils = scriptUtils;
        this.init();
    }

    public Interpolator getYajswInterpolator() {
        return this._interpolator;
    }

    public void init() {
        if(!this._init) {
            if(this._scriptUtils == null) {
                this._scriptUtils = new HashMap();
            }

            if(this._localConfiguration != null) {
                this._systemConfiguration.addConfiguration(this._localConfiguration);
                if(this.debug) {
                    this.log.debug("added local configuration ");
                }
            }

            if(this._useSystemProperties) {
                this._systemProperties = ConfigurationConverter.getConfiguration((Properties)System.getProperties().clone());
                this._systemConfiguration.addConfiguration(this._systemProperties);
                if(this.debug) {
                    this.log.debug("added system configuration ");
                }
            }

            this._systemConfiguration.addConfiguration(new MapConfiguration((Map)(!OperatingSystem.instance().isPosix()?new CaseInsensitiveMap(System.getenv()):new HashMap(System.getenv()))));
            this.addConfiguration(this._systemConfiguration);
            String configFile = (String)this.getProperty("wrapper.config");
            if(configFile != null && configFile.contains("\"")) {
                configFile = configFile.replaceAll("\"", "");
            }

            String e;
            if(configFile == null) {
                if(this.debug) {
                    this.log.warn("configuration file not set");
                }
            } else if(!this.fileExists(configFile)) {
                this.log.error("configuration file not found: " + configFile);
            } else {
                if(configFile.endsWith(".jnlp")) {
                    try {
                        JnlpSupport it = new JnlpSupport(configFile);
                        this._fileConfiguration = it.toConfiguration((String)this.getProperty("wrapperx.default.config"));
                        this._fileConfiguration.setFileName(configFile);
                        this.addConfiguration(this._fileConfiguration);
                    } catch (Exception var12) {
                        var12.printStackTrace();
                        return;
                    }
                }

                if(this._fileConfiguration == null) {
                    try {
                        VFSFileSystem it1 = new VFSFileSystem();
                        it1.setLogger(new CommonsLoggingAdapter(this.log));
                        it1.setFileOptionsProvider(new FileOptionsProvider() {
                            public Map getOptions() {
                                HashMap result = new HashMap();
                                String httpProxy = System.getProperty("http.proxyHost");
                                String httpPort = System.getProperty("http.proxyPort");
                                if(httpProxy != null) {
                                    int port = 8080;
                                    if(httpPort != null) {
                                        try {
                                            port = Integer.parseInt(httpPort);
                                        } catch (Exception var6) {
                                            var6.printStackTrace();
                                        }
                                    }

                                    result.put("proxyHost", httpProxy);
                                    result.put("proxyPort", Integer.valueOf(port));
                                }

                                return result;
                            }
                        });
                        FileSystem.setDefaultFileSystem(it1);
                        this._fileConfiguration = new PropertiesConfiguration();
                        this._fileConfiguration.setFileName(configFile);
                        this._fileConfiguration.append(this._systemConfiguration);


                        this._fileConfiguration.load();
                        e = this._fileConfiguration.getString("wrapper.conf.encoding");
                        if(e != null) {
                            this._fileConfiguration = new PropertiesConfiguration();
                            this._fileConfiguration.setEncoding(e);
                            this._fileConfiguration.setFileName(configFile);
                            this._fileConfiguration.append(this._systemConfiguration);


                            this._fileConfiguration.load();
                        }

                        this.addConfiguration(this._fileConfiguration);
                    } catch (ConfigurationException var11) {
                        this.log.error("error loading configuration file <init>AsjwConfiguration", var11);
                    }
                }

                if(!this.isLocalFile()) {
                    if(this._fileConfiguration.getProperty("wrapper.working.dir") == null) {
                        try {
                            this._fileConfiguration.setProperty("wrapper.working.dir", (new File(this.getCache())).getCanonicalPath().replaceAll("\\\\", "/"));
                        } catch (IOException var8) {
                            var8.printStackTrace();
                        }
                    }

                    if(this._fileConfiguration.containsKey("wrapper.cache")) {
                        this._fileConfiguration.setProperty("wrapper.cache", this.getCache());
                    }
                }
            }

            this._isStopper = this.getBoolean("wrapper.stopper", false);

            try {
                this._isJavaDebug = this.getInt("wrapper.java.debug.port", -1) != -1;
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            Iterator it2 = this.getKeys("wrapper.config.script");

            while(it2.hasNext()) {
                try {
                    e = (String)it2.next();
                    String script = this.getString(e);
                    String bind = e.substring(e.lastIndexOf(".") + 1);
                    this._scriptUtils.put(bind, new GroovyScript(script, "", (WrappedProcess)null, (String[])null, 0, this.log, (String)null, false, 1));
                } catch (Exception var6) {
                    this.log.error("error reading script", var6);
                }
            }

            this._init = true;
        }
    }

    private Interpolator createGInterpolatornew(Configuration conf, boolean b, String[] object, Map utils) {
        Interpolator result = null;

        try {
            Class e = conf.getClass().getClassLoader().loadClass("org.apache.commons.configuration.GInterpolator");
            Constructor rc = e.getDeclaredConstructor(new Class[]{Configuration.class, Boolean.TYPE, String[].class, Map.class});
            result = (Interpolator)rc.newInstance(new Object[]{conf, Boolean.valueOf(b), object, utils});
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return result;
    }

    private boolean fileExists(String file) {
        try {
            DefaultFileSystemManager e = (DefaultFileSystemManager)VFS.getManager();
            FileObject f = VFSUtils.resolveFile(".", file);
            return f.exists();
        } catch (FileSystemException var4) {
            var4.printStackTrace();
            return false;
        }
    }

    boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    protected Object resolveContainerStore(String key) {
        Integer result = null;
        if(key == null) {
            result = null;
        }

        if(this._isJavaDebug) {
            if(key.equals("wrapper.startup.timeout")) {
                result = Integer.valueOf(2147483);
            } else if(key.equals("wrapper.shutdown.timeout")) {
                result = Integer.valueOf(2147483);
            } else if(key.equals("wrapper.ping.timeout")) {
                result = Integer.valueOf(2147483);
            }
        }

        if(result != null) {
            return result;
        } else {
            Object result1;
            if(!this._isStopper) {
                result1 = super.resolveContainerStore(key);
            } else {
                if(key.startsWith("wrapper.on_exit")) {
                    return null;
                }

                if(key.startsWith("wrapper.exit_on_main_terminate")) {
                    result1 = "0";
                } else if(key.startsWith("wrapper.daemon")) {
                    result1 = null;
                } else if(key.contains(".script")) {
                    result1 = null;
                } else if(key.contains(".filter")) {
                    result1 = null;
                } else if(key.contains(".pidfile")) {
                    result1 = null;
                } else if(key.contains(".ntservice")) {
                    result1 = null;
                } else if(key.contains(".jmx")) {
                    result1 = null;
                } else if(key.contains(".lockfile")) {
                    result1 = null;
                } else if(key.contains(".stop.conf")) {
                    result1 = null;
                } else if(key.equals("wrapper.tray")) {
                    result1 = null;
                } else {
                    result1 = super.resolveContainerStore(key);
                }
            }

            if(this._interpolator != null && result1 != null && !result1.equals(this._interpolator.interpolate(result1))) {
                this._interpolated.add(key);
            }

            return result1;
        }
    }

    public Set getLookupSet() {
        return this._interpolated;
    }

    public Map<String, String> getEnvLookupSet() {
        if(this._interpolator != null) {
            Map result = this._interpolator.getUsedEnvVars();
            result.putAll(this._interpolator.getFromBinding());
            return result;
        } else {
            return new HashMap();
        }
    }

    public static void main(String[] args) {
        YajswConfigurationImpl c = new YajswConfigurationImpl();
        c.setProperty("t1", "x");
        c.setProperty("t2", "${t1}");
        System.out.println(c.getString("t2"));
        Iterator it = c.getInterpolator().prefixSet().iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }

    public CompositeConfiguration getSystemConfiguration() {
        return this._systemConfiguration;
    }

    public void reload() {
        if(this._fileConfiguration != null) {
            this._fileConfiguration.reload();
        }

    }

    public boolean isLocalFile() {
        if(this._fileConfiguration == null) {
            return true;
        } else {
            try {
                String ex = this._fileConfiguration.getFileName();
                if(ex.endsWith(".jnlp")) {
                    return false;
                } else {
                    FileObject f = VFSUtils.resolveFile(".", ex);
                    return VFSUtils.isLocal(f);
                }
            } catch (Exception var3) {
                var3.printStackTrace();
                return true;
            }
        }
    }

    public String getCache() {
        return this.getString("wrapper.cache", "yajsw_cache");
    }

    public String getCachedPath() {
        return this.getCachedPath(true);
    }

    public String getCachedPath(boolean save) {
        if(this._fileConfiguration == null) {
            return null;
        } else {
            if(this.isLocalFile()) {
                try {
                    return (new File(this._fileConfiguration.getURL().toURI())).getCanonicalPath();
                } catch (IOException var8) {
                    var8.printStackTrace();
                    return this._fileConfiguration.getFileName();
                } catch (URISyntaxException var9) {
                    var9.printStackTrace();
                }
            }

            try {
                String ex = this.getCache() + "/conf";
                String fileName = this._fileConfiguration.getFileSystem().getFileName(this._fileConfiguration.getFileName());
                File cf = new File(ex);
                if(!cf.exists()) {
                    cf.mkdirs();
                }

                if(fileName.endsWith(".jnlp")) {
                    fileName = fileName + ".conf";
                }

                File cn = new File(cf, fileName);
                if(save) {
                    PropertiesConfiguration c2 = (PropertiesConfiguration)this._fileConfiguration.interpolatedConfiguration();
                    c2.save(cn);
                }

                return cn.getCanonicalPath();
            } catch (Exception var7) {
                var7.printStackTrace();
                return this._fileConfiguration.getFileName();
            }
        }
    }

    public Configuration getFileConfiguration() {
        return this._fileConfiguration;
    }

    public String getString(String key) {
        String value = super.getString(key);
        return value;
    }

    public long getConfigFileTime() {
        return -1L;
    }

    public boolean isStopper() {
        return this._isStopper;
    }
}
