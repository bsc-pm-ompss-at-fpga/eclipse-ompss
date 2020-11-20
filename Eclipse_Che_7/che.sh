vagrant ssh -c"sudo chectl server:stop"
vagrant ssh -c"sudo chectl server:start --platform=minikube --installer=helm"
vagrant ssh -c"cp ~/cheCA.crt /cert/"
