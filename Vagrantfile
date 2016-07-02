# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"

  config.vm.network "forwarded_port", guest: 3000, host: 3000
  config.vm.network "forwarded_port", guest: 5858, host: 5858
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 27017, host: 27017

  config.vm.provider "virtualbox" do |v|
    v.memory = 2048
    v.cpus = 2
  end

  config.vm.provision "shell", inline: <<-SHELL
    export JAVA_VERSION='8'
    export JAVA_HOME='/usr/lib/jvm/java-8-oracle'

    sudo apt-get -y update

    sudo apt-get -y install gcc make build-essential autoconf nasm unzip

    # install Java 8
    echo 'deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main' >> /etc/apt/sources.list
    echo 'deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main' >> /etc/apt/sources.list
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys C2518248EEA14886

    apt-get update

    echo oracle-java-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
    apt-get install -y --force-yes oracle-java${JAVA_VERSION}-installer
     update-java-alternatives -s java-8-oracle

    # install maven
    sudo apt-get install -y maven

    # install node
    curl -sL https://deb.nodesource.com/setup_0.12 | sudo bash -
    sudo apt-get install -y nodejs

    # install nodemon
    sudo npm install -g nodemon

    # install node-gyp as a mongoose dependency
    sudo npm install -g node-gyp

    # install gulp
    sudo npm install -g gulp

    # install jshint
    sudo npm install -g jshint

    # install optipng-bin (to avoid npm install errors)
    sudo npm install -g optipng-bin

  SHELL

end
