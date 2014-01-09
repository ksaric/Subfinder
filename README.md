Subfinder
=========

A project designed to quickly find all the subtitles of a movie, series, or a recursive folder containing them.

Requires Java 7.

Currently using SubsMax subtitles(currently down, not working, maintence). Switch to:
 -  http://www.allsubs.org/                 (USED TO READ THE API, no download link!)
 -  http://www.subtitlesource.org/help/api  (JUST FOR A WEB INTERFACE, no client support!)
 -  http://www.sublight.me/download         (Link points to the windows client program?)
 -  http://subsmax.com/subtitles-api/       (Currently down for maintence, for 5 consecutive days!?)
 -  http://trac.opensubtitles.org/projects/opensubtitles (XML RPC!)
 -  http://thesubdb.com/api/                (Uses actual movie hash, requires reading the movie file)
 -  http://www.api.subtitleseeker.com:82/   (Waiting for a response)

Using Guice Multibindings for a flexibile architecture(plugins for other sub providers).

Ideas:
=========

Using ExecutorService, and than testing Actors and maybe STM(Akka or JetLang)?
AsyncClientHttp?
"Inspecting Multibindings or MapBindings" for an even more flexibile plugin solution?

