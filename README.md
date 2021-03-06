# swagger-gradle-plugin    
[![Build Status](https://travis-ci.org/zhurlik/gradle-swagger-plugin.svg?branch=master)](https://travis-ci.org/zhurlik/gradle-swagger-plugin)
[![Coverage Status](https://coveralls.io/repos/zhurlik/gradle-swagger-plugin/badge.png)](https://coveralls.io/r/zhurlik/gradle-swagger-plugin)    

## About
This plugin executes the standard maven plugin for Swagger.

## BuildScript
Note that for using this guy you have to added into classpath under buildscript required swagger jars.
```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
    mavenCentral()
  }
  
  dependencies {
    classpath "gradle.plugin.com.github.zhurlik:gradle-swagger-plugin:0.2"
    // swagger
    classpath group: 'io.swagger', name: 'swagger-core', version: '1.5.+'
    classpath group: 'com.github.kongchen', name: 'swagger-maven-plugin', version: '3.1.+'
  }
```
## Swagger Task
After adding this plugin you will have the task [SwaggerTask](https://github.com/zhurlik/gradle-swagger-plugin/blob/master/src/main/groovy/com/github/zhurlik/SwaggerTask.groovy) that has 2 important attributes:
* **classesDirs** - to specify dirs for scanning classes with Swagger annotations
* **apiSources** - to declare a list of ApiSource

## How to use    
You can find a simple example [here](samples/build.gradle)    

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
    mavenCentral()
  }
  
  dependencies {
    classpath "gradle.plugin.com.github.zhurlik:gradle-swagger-plugin:0.2"
    // swagger
    classpath group: 'io.swagger', name: 'swagger-core', version: '1.5.+'
    classpath group: 'com.github.kongchen', name: 'swagger-maven-plugin', version: '3.1.+'
  }
}

apply plugin: "com.github.zhurlik.swagger"    

task('swaggertest', type: SwaggerTask) {

    // any dirs with classes
    classesDirs = [
        file('classes/main')
        // project(':your-project').sourceSets['main'].output.classesDir
    ]

    apiSources = [
            new ApiSource(
                    springmvc: false,
                    locations: ['com/github/kongchen/swagger/sample/wordnik/resource'],
                    schemes: ['http', 'https'],
                    host: 'petstore.swagger.wordnik.com',
                    basePath: '/api',
                    info: new Info(
                            title: 'Swagger Maven Plugin Sample',
                            version: 'v1',
                            description: 'This is a sample for swagger-maven-plugin',
                            termsOfService: 'http://www.github.com/kongchen/swagger-maven-plugin',
                            contact: new Contact(
                                    email: 'kongchen@gmail.com',
                                    name: 'Kong Chen',
                                    url: 'http://kongch.com'
                            ),
                            license: new License(
                                    url: 'http://www.apache.org/licenses/LICENSE-2.0.html',
                                    name: 'Apache 2.0'
                            )
                    ),
                    outputPath: file("${buildDir}/swagger/document.html").path,
                    swaggerDirectory: file("${buildDir}/swagger/swagger-ui").path,
                    templatePath: file("templates/strapdown.html.hbs")
            )
    ]
}
```
