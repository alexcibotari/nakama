var path = require('path');
var ExtractTextPlugin = require("extract-text-webpack-plugin");

module.exports = {
    entry: ['./src/main/webapp/app.js'],
    devtool: 'sourcemaps',
    cache: true,
    debug: true,
    output: {
        path: __dirname,
        filename: './src/main/webapp/built/bundle.js'
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    presets: ['react']
                }
            },
            {test: /\.css$/, loader: ExtractTextPlugin.extract("style-loader", "css-loader")}
        ]
    },
    plugins: [
        new ExtractTextPlugin("./src/main/webapp/built/styles.css")
    ]
};