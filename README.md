[![](https://img.shields.io/badge/discord-join%20chat!-7289DA.svg)](https://discord.gg/vWYembz)

Library to work with the Minecraft's protocol in Photon. Based on the amazing [Steiveice10's MCProtocolLib](https://github.com/Steveice10/MCProtocolLib).

## How to build
Photon uses [gradle](http://gradle.org) to manage its dependencies. Building the ProtocolLib project is very easy:

1. Install the Java 8 SDK (aka JDK 8) if you don't already have it.
2. Put the [Photon Server](https://github.com/mcphoton/Photon-Server) in the same directory as the ProtocolLib project folder.
3. In the ProtocolLib project's directory, run this command: `./gradlew build`

## How to contribute
* The ProtocolLib is designed to work with the Photon server and API. See [the repo](https://github.com/mcphoton/Photon-Server).
* Information about the packets can be found [here](http://wiki.vg/Protocol).
* Please respect [the coding style](https://github.com/mcphoton/Photon-Server/blob/develop/Coding%20Style.md). WARNING: For the ProtocolLib's java files, use 4 spaces instead of tabs. The reason is that it's the indent type chosen by the MCProtocolLib's developer.
* Good java skills are required.

## Branches
The *develop* branch contains the latest **in development** version of the Photon protocol library. More stable releases can be found in the *master* branch.

## Current status
The Protocol library is currently being adapted to the Photon Server and is unstable.

## License
**This project is licensed under the terms of the GNU Lesser General Public License version 3**, which you can find in the LICENSE file.
This program includes code from the [MCProtocolLib](https://github.com/Steveice10/MCProtocolLib) and [PacketLib](https://github.com/Steveice10/PacketLib) libraries, both used under the following license:
```
Copyright (C) 2013-2017 Steveice10

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
Any addition and modification to the code of these libraries is subject to the terms of the GNU Lesser General Public License version 3.  
Unmodified versions of MCProtocolLib and PacketLib can be found at their respective github repositories ([here](https://github.com/Steveice10/MCProtocolLib) and [here](https://github.com/Steveice10/PacketLib)).
