scala_library(name = 'auth',
  dependencies = [
     'projects3/rOut/generic/src/io/rout/decode:decoding',
     '3rdparty/jvm/com/twitter:finagle-http',
     '3rdparty/jvm/com/twitter:finagle-stats',
     '3rdparty/jvm/com/twitter:twitter-server',
  ],
    sources = globs('*.scala'),
) 

jvm_binary(name='binary',
  basename='rOut-app',
  main='io.rout.auth.Main',
  dependencies=[
  ':auth'
  ]
)