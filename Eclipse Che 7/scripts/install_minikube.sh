sudo apt-get install -y conntrack
curl -Lo minikube https://github.com/kubernetes/minikube/releases/download/v1.10.0-beta.2/minikube-linux-amd64 && chmod +x minikube
sudo mkdir -p /usr/local/bin/
sudo install minikube /usr/local/bin/