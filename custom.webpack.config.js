var webpack = require('webpack');

module.exports = {
    "entry": {
        "social-sharer-fastopt": ["/Users/dayeonhwang/structured-data-bookmarker/target/scala-2.12/scalajs-bundler/main/social-sharer-fastopt.js"]
    },
    "output": {
        "path": "/Users/dayeonhwang/structured-data-bookmarker/target/scala-2.12/scalajs-bundler/main",
        "filename": "[name]-bundle.js"
    },
    "devtool": "source-map",
    "module": {
        "preLoaders": [{
            "test": new RegExp("\\.js$"),
            "loader": "source-map-loader"
        }]
    },
    "plugins": [
        new webpack.ProvidePlugin({
            'jQuery': 'jquery',
            '$': 'jquery',
            'global.jQuery': 'jquery' })
    ]
};

