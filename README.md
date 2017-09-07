# structured-data-bookmarker

Structured Data Bookmarker uses Scala.js to crawl webpages and display the structured data as a bookmarklet of a browser.

## What is structured data?
Structured data is markup of a webpage that enables search engines to better understand the information and use webpage content. Search engines use structured data to generate richer snippets of webpages. So how do we find and manage structured data of webpages? Structured data are metadata that are included in `<head>...</head>` code of a webpage. Among those metadata, `og` metadata are used to turn webpages into graph objects called open graph (in short, og). Examples of these `og` metadata are `og:title`, `og:description`, `og:url` and `og:image`. In the end, it is important to understand how structured data works (not only what they are but also how to make use of them) to make sure your webpage is represented well on search engines and reach your goals. If you're curious how it works, you can read more about [the Open Graph](http://ogp.me/) and [Structured Data](https://developers.google.com/search/docs/guides/intro-structured-data).

## What is Scala.js?
If you code purely in Scala and want to use beautiful tools that are more Javascript-friendly(npm packages, Node.js, DOM, etc), you might run into troubles. How do we make use of Scala code and Javascript environment for front-end applications? Well, we can use Scala.js --  a compiler that compiles Scala source code to equivalent Javascript code. This is really amazing because now you can write Scala code that can be run in a browser! That means we can write client-side web applications in Scala!! More info [here](http://www.lihaoyi.com/hands-on-scala-js/). For this project, I used `Scalajs-bundler` which is a SBT plugin that bundles npm dependencies with `.js` file emitted by Scala.js compiler and generates a final `...fastopt-bundle.js` that's ready to be ran on a browser.

## How do I use Structured Data Bookmarker?

### Using scalajs-bundler

Add Scalajs-bundler SBT plugin in `project/plugins.sbt`:
```scala
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.7.0")
```

Enable Scalajs-bundler in `build.sbt`:
```scala
lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSBundlerPlugin)
```

### Setting up library dependencies
For this projecct, I used Ammonite Ops, Scalajs-dom and Scalajs-jquery. 
* [Ammonite Ops](http://ammonite.io/#Ammonite-Ops): Scala scripting tool for filesystem operations
* [Scalajs-dom](http://scala-js.github.io/scala-js-dom/): Scala interface to access DOM elements in pure Scala code without depending on `js.Dynamic` or Javascript.
* [Scalajs-jquery](https://github.com/scala-js/scala-js-jquery): jQuery facade type for Scala.js

Add those libraries in `build.sbt`:
```scala
libraryDependencies ++= Seq(
  "com.lihaoyi" %% "ammonite-ops" % "1.0.0-RC9",
  "org.scala-js" %%% "scalajs-dom" % "0.9.3",
  "be.doeraene" %%% "scalajs-jquery" % "0.9.2"
)
```

### Setting up npm dependencies
For this project, I used jQuery and jQueryUI npm packages to use its "dialog" widget so that it pops up on test websites and displays the structured data in a dialog. Because Scala.js compiler creates `package.json` by itself and updates/handles npm dependencies all for you, you just have to update `build.sbt` file with the correct version of each npm dependency. You can check your npm package's version [here](https://www.npmjs.com/package/jquery-ui).

Add those dependencies in `build.sbt`:
```scala
npmDependencies in Compile += "jquery" -> "2.1.3"
npmDependencies in Compile += "jquery-ui" -> "1.12.1"
```

### Running
Use `sbt` to start SBT

```bash
$ sbt
```

Run `fastOptJS::webpack` to bundle all dependencies and generate JavaScript bundle (...-fastopt-bundle.js) including jQuery and jQueryUI as specified in the npmDependencies sbt build definition setting:

```bash
$ fastOptJS::webpack
```

Run `~fastOptJS::webpack` to bundle in a watch mode:

```bash
$ ~fastOptJS::webpack
```

### Using Python SimpleHTTPServer
Save the following Python file as `simple-https-server.py`

```bash
import BaseHTTPServer, SimpleHTTPServer
import ssl

httpd = BaseHTTPServer.HTTPServer(('localhost', 4443), SimpleHTTPServer.SimpleHTTPRequestHandler)
httpd.socket = ssl.wrap_socket (httpd.socket, certfile='./server.pem', server_side=True)
httpd.serve_forever()
```

Run server at same directory level

```bash
python simple-https-server.py
```

### Using Structured Data Bookmarker on browser
1. Open `scalajs-index.html` on browser
2. Drag the bookmarklet hyperlink to a browser tab
3. Navigate to a test webpage
4. Click the bookmarklet


