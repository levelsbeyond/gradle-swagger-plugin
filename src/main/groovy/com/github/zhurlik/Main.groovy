package com.github.zhurlik

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kongchen.swagger.docgen.mavenplugin.ApiDocumentMojo
import com.github.kongchen.swagger.docgen.mavenplugin.ApiSource

class Main {
    static void main(String[] args) {
        if (args.length != 1) {
            println "Must pass 1 arg that is a serialized array of ApiSource objects"
        }

        println "Making Swagger json..."

        def apiSources = new ObjectMapper().readValue(args[0], new TypeReference<List<ApiSource>>() {}) as List<ApiSource>
        def mavenTask = new ApiDocumentMojo()
        mavenTask.setApiSources(apiSources)

        mavenTask.execute()
    }
}