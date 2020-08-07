# Introduce to Eclicpse Vert.x

[![Build Status](https://travis-ci.org/hakdogan/IntroduceToEclicpseVert.x.svg?branch=master)](https://travis-ci.org/hakdogan/IntroduceToEclicpseVert.x)
![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=hakdogan_IntroduceToEclicpseVert.x&metric=alert_status)
![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=hakdogan_IntroduceToEclicpseVert.x&metric=sqale_rating)
![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=hakdogan_IntroduceToEclicpseVert.x&metric=reliability_rating)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=kodcu%3Avertx&metric=coverage)](https://sonarcloud.io/dashboard?id=kodcu%3Avertx)

This repository contains the code of Vert.x examples contained in my articles published on platforms such as [kodcu.com](https://kodcu.com/author/hakdogan/), [medium](https://medium.com/@hakdogan), [dzone](https://dzone.com/users/1161493/hakdogan.html). How to run each example is described in its readme file.

These articles are:

* [Introduce to Eclipse Vert.x](https://medium.com/@hakdogan/introduce-to-eclicpse-vert-x-1d24c97643c7)
* [How to Work With Multiple Verticles and Communication in Vert.x](https://medium.com/@hakdogan/working-with-multiple-verticles-and-communication-between-them-in-vert-x-2ed07e8e6425)
* [How to Run a Vert.x Cluster With Broadcasting Messaging](https://medium.com/@hakdogan/how-to-run-a-vert-x-cluster-with-broadcasting-messaging-fc79ff113c9c)
* [How to Run Blocking Code in Vert.x](https://medium.com/@hakdogan/how-to-run-blocking-code-in-vert-x-174dad7e0f94)
* [How to Share Data Between Threads in Vert.x](https://medium.com/@hakdogan/how-to-share-data-between-threads-in-vert-x-afdf26dcc684)
* [How to Call the Next Handler in Vert.x?](https://medium.com/@hakdogan/how-to-call-the-next-handler-in-vert-x-c498506c427c)

This branch contains the migrated version to `Java 9 Modularity System` of the `master branch` except for [shared data provider](https://github.com/hakdogan/IntroduceToEclicpseVert.x/tree/master/SharedDataProvider) and [reader](https://github.com/hakdogan/IntroduceToEclicpseVert.x/tree/master/SharedDataReader) maven modules. These two modules could not be modularized because they use both `vertx.web` and `vertx-web-templ-freemarker` modules and the use of the two together brings the problem of [split-packages](https://twitter.com/hakdoganhoca/status/1258710422754070531/). If you want to examine `non-moduler` version, please visit the `master branch`.