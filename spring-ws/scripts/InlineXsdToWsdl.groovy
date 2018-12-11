import groovy.io.FileType

static String getXsdFileContent(String pathName, boolean trimSchema) {
    def xsdFile = new File(pathName)
    def xsdContent = xsdFile.getText("UTF-8")
    if (trimSchema) {
        def idx1 = xsdContent.indexOf("<xsd:schema")
        def idx2 = xsdContent.indexOf(">", idx1)
        xsdContent = xsdContent.substring(idx2 + 1, xsdContent.length())
        xsdContent = xsdContent.replace("</xsd:schema>", "")
    }
    return xsdContent
}

static void processWsdlFile(File wsdlFile, boolean isVerbose) {
    if (isVerbose) {
        println "processing wsdl: " + wsdlFile
    }
    StringBuilder result = new StringBuilder()
    wsdlFile.eachLine { line ->
        def idx = line.indexOf("<xsd:include schemaLocation=\"")
        if (idx != -1) {
            def idx2 = line.indexOf("\"/>", idx)
            def xsdFileName = line.substring(idx + "<xsd:include schemaLocation=\"".length(), idx2)
            def wsdlDir = wsdlFile.getParentFile().getAbsolutePath()
            String pathName = wsdlDir + "/" + xsdFileName
            String xsdContent = getXsdFileContent(pathName, true)
            result.append(xsdContent)
            return
        }
        result.append(line).append('\n')
    }
    wsdlFile.withWriter("UTF-8") {
        writer -> writer.write(result.toString())
    }
}

static void inlineXsdToWsdl(String wsdlDirectory, boolean isVerbose) {
    new File(wsdlDirectory).eachFileRecurse(FileType.FILES) { file ->
        if (!file.getName().endsWith(".wsdl")) {
            return
        }
        processWsdlFile(file, isVerbose)
    }
}

static void deleteAllXsdFiles(String wsdlDirectory, boolean isVerbose) {
    new File(wsdlDirectory).eachFileRecurse(FileType.FILES) { file ->
        if (file.getName().endsWith(".xsd")) {
            if (isVerbose) {
                println "deleting xsd: " + file
            }
            file.delete()
        }
    }
}

def isVerbose = "true".equalsIgnoreCase(properties['verbose'])

inlineXsdToWsdl(properties['wsdlDir'], isVerbose)
deleteAllXsdFiles(properties['wsdlDir'], isVerbose)
