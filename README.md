# Nerdvana
What happens when a Buddhist becomes totally absorbed with the computer he is working with? He enters Nerdvana.

## Installation

### Prerequisites

1. Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
2. Install [Vagrant](https://www.vagrantup.com/)
3. Install [Spring Tool Suite (STS)](http://spring.io/tools/sts/all), an IDE specific for Java development using the Spring framework.
4. Install a text editor of your choice for front end development - you may optionally also do this within STS.

### Get Running
1. Use your Terminal and `cd` into the `Nerdvana` repo
2. Run `vagrant up`
    
    This command "creates and configures the VM according to your vagrantfile". See (https://www.vagrantup.com/docs/cli/up.html)[https://www.vagrantup.com/docs/cli/up.html] for more details.

    Please note that the initial creation may take a long time (~ 30 minutes) to install all the needed dependencies (Java, Node, etc.). Please be patient!
5. After the vagrant box is setup and up to date, run `vagrant ssh`.
    
    This command tunnels you into your VM. See (https://www.vagrantup.com/docs/cli/ssh.html)[https://www.vagrantup.com/docs/cli/ssh.html] for more info.
6. `cd /vagrant`
    
    Go to the `/vagrant` directory. This is directory is synced to your computer's Nerdvana directory.
7. Run `npm install`
    
    This will install all the node.js dependencies.
8. `gulp serve`
    
    This will get the front end running
9. The app should be running at [http://localhost:3000](http://localhost:3000) in your browser

### Launch the backend server

#### Via Command Line

1. Open another tab/window in Terminal. Make sure you are in the `Nerdvana` directory.
2. Run `vagrant ssh`
3. `cd /vagrant`
4. Use Maven to start the server by typing `./mvnw spring-boot:run`

#### Via STS

Instead of running from the Terminal, you can launch the backend server within the Spring Tool Suite IDE. 

_Configuration Instructions coming soon..._

#### Verify the Back End is Running

From the command line, type `curl localhost:8080/api` to see available endpoints.

#### A Quick Note

For now, the front end app runs on a separate server ([Browsersync](https://www.browsersync.io/)), which will allow for faster front end development, on [http://localhost:3000](http://localhost:3000). The back end server is [Tomcat](https://tomcat.apache.org/), running on [http://localhost:8080](http://localhost:8080). The front end will talk to the back end via `/api` endpoints, so the front end server is set up to proxy any calls to `/api` to the back end server on 8080. 

#### Coming Soon
We will be introducing a build step that will integrate front end code, so that everything be served by Tomcat.
