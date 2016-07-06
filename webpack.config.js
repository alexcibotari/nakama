var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var ROOT_PATH = path.resolve(__dirname);
var BUILD_PATH = path.join(ROOT_PATH, '/src/main/webapp/build/');
var JSX_PATH = path.join(ROOT_PATH, '/src/main/jsx/');

module.exports = {
    entry: ['./src/main/jsx/app.jsx'],
    /*devtool: 'sourcemaps',*/
    cache: true,
    debug: true,
    resolve: {
     extensions: ['', '.js', '.jsx']
     },
    output: {
        path: __dirname,
        filename: './src/main/webapp/build/bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                //include: JSX_PATH,
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    presets: ['es2015', 'react'],
                    cacheDirectory: true
                }
            },
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract("style-loader", "css-loader")
            }
        ]
    },
    plugins: [
        new ExtractTextPlugin("./src/main/webapp/build/styles.css")
    ]
};