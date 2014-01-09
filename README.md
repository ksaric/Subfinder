Subfinder
=========

A project designed to quickly find all the subtitles of a movie, series, or a recursive folder containing them.

Requires Java 7.

Currently using SubsMax subtitles(currently down, not working, maintence). Switch to:
 -  http://www.allsubs.org/                 (USER TO READ THE API, no download link!)
 -  http://www.api.subtitleseeker.com:82/
 -  http://thesubdb.com/api/

Using Guice Multibindings for a flexibile architecture(plugins for other sub providers).

Ideas:
=========

Using ExecutorService, and than testing Actors and maybe STM(Akka or JetLang)?
"Inspecting Multibindings or MapBindings" for an even more flexibile plugin solution?

