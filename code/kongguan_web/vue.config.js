module.exports = {
  runtimeCompiler: true,
  lintOnSave: process.env.NODE_ENV !== 'production',
  lintOnSave: false,
  productionSourceMap:false,
  chainWebpack(config) {
      config.plugins.delete('prefetch')  
  },
  pwa: {
      iconPaths: {
      }
  },
  devServer: {
      host: "0.0.0.0",
      port: 8089,
      proxy: {
          '/api': {
              target: process.env.VUE_APP_BASE_API,
              pathRewrite:{"^/api":"/"},
              changeOrigin: true,
              ws: false
          },
      }
  }
}