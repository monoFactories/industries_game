module core{
    requires registries;
    requires engine;
    requires languages;
    requires interfaceFx;
    requires math;
    requires versioncompare;
    requires jdk.compiler;
    requires zt.zip;

    opens mono.factories.core.inital;
    opens mono.factories.core.interfaces.engine;
    opens mono.factories.mod;
}