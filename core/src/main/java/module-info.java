module core{
    requires registries;
    requires engine;
    requires languages;
    requires interfaceFx;
    requires math;
    requires versioncompare;

    opens mono.factories.core.inital;
    opens mono.factories.core.interfaces.engine;
    opens mono.factories.mod;
}