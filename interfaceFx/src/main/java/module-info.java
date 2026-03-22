module interfaceFx {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    requires registries;
    requires java.desktop;

    exports mono.factories.interfacefx.components;
    exports mono.factories.interfacefx.handlers;
    exports mono.factories.interfacefx.nodeparameters;
    exports mono.factories.interfacefx.registries;
}


/*


<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>project.init</groupId>
        <artifactId>industries_game</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>interfaceFx</artifactId>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <javafx.version>25.0.1</javafx.version>
        <javafx.platform>win</javafx.platform>
    </properties>

    <dependencies>
        <!-- JavaFX dependencies -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
            <classifier>${javafx.platform}</classifier>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
            <classifier>${javafx.platform}</classifier>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-media</artifactId>
            <version>${javafx.version}</version>
            <classifier>${javafx.platform}</classifier>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-base</artifactId>
            <version>${javafx.version}</version>
            <classifier>${javafx.platform}</classifier>
        </dependency>

        <!-- Ваша зависимость -->
        <dependency>
            <groupId>project.init</groupId>
            <artifactId>registries</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <compilerArgs>
                        <arg>--module-path</arg>
                        <arg>${java.home}/../lib</arg>
                        <arg>--add-modules</arg>
                        <arg>javafx.controls,javafx.fxml,javafx.media,javafx.base</arg>
                    </compilerArgs>
                </configuration>
            </plugin>

            <!-- Плагин для работы с JavaFX -->
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <options>--module-path ${java.home}/../lib</options>
                    <mainClass>mono.factories.core.registry.RegistryContainer</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>


*/