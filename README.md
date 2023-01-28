# D2SProtrackerParser
The secondary microservice dedicaded solely to retrieve IDs of recent dota2 games played on professional skil level.

The old algorythm was based on parsing the main page of [dota2protracker.com](https://www.dota2protracker.com/), it contained around 60-70 last played professional matches. While rewriting the code from monolith ProDB I noticed the problem with frecuency of updates on the main page - it barely updated and only gathered about 200 matches in 24h cycle. Although [the heroes page](https://www.dota2protracker.com/hero/Rubick) clearly updated so protracker has all its functional, it only doesn't update the main page.
_______________________________________________________
So currently the service just whaits till it gets the /parse http-get, and it is getting one per hour from D2SHistoryRefiller. My next idea is to have cycle with 124 steps - a step per dota 2 hero and parse the heroes pages - this is thw work in progress.
