import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "sparqlstream-web"
    val appVersion      = "1.0.6"

    val appDependencies = Seq(
      // Add your project dependencies here,

      "es.upm.fi.oeg.morph.streams" % "adapter-esper" % "1.0.9",
      "es.upm.fi.oeg.morph.streams" % "adapter-gsn" % "1.0.7"//,
      //("es.upm.fi.oeg.morph.streams" % "wrappers" % "1.0.7")
       // .exclude("org.slf4j", "slf4j-log4j12")
        //"ch.qos.logback" % "logback-classic" % "1.0.9"
    )
    
    // Dependency on wrappers sub-project
    lazy val wrappers = RootProject(file("../../morph-streams/wrappers"))

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here 
        //scalacOptions ++= Seq("-unchecked", "-deprecation", "-target:jvm-1.6", "-feature"),
        scalacOptions ++= Seq("-unchecked", "-deprecation", "-target:jvm-1.7", "-feature"),
        //javacOptions ++= Seq("-source", "1.6", "-target", "1.6"),
        javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),
        resolvers ++= Seq(
         //DefaultMavenRepository,         
         //("Local ivy Repository" at "file://"+Path.userHome.absolutePath+"/.ivy2/local")
         Resolver.url("local ivy",url("file://"+Path.userHome.absolutePath+"/.ivy2/local"))(Resolver.ivyStylePatterns),
         "aldebaran-libs" at "http://aldebaran.dia.fi.upm.es/artifactory/sstreams-external-libs-local",
         "aldebaran-releases" at "http://aldebaran.dia.fi.upm.es/artifactory/sstreams-releases-local",
         "plord" at "http://homepages.cs.ncl.ac.uk/phillip.lord/maven"
         //"jpc-repo" at "https://github.com/jpcik/jpc-repo/raw/master/repo"   
        )       
    ).dependsOn(wrappers).aggregate(wrappers)


}
