# algo-book
Collection of math and CS algorithms and concepts.
This is a work in progress. I am building this library while solving exercises from Knuth's "The Art of Computer Programming" 
and [Project Euler](https://projecteuler.net/) challenges and occasionally 
LeetCode and Codility.    


### development notes 
- Use gradle 6.4+ for builds (supports Java modules)
- OSX Big Sur sets explicit `JAVA_HOME` as `/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home`, which is JRE and 
misses many features required for gradle. Make sure to point `JAVA_HOME` to JDK.