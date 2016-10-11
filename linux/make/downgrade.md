### downgrade make from 3.82 to 3.81

curl -O [https://ftp.gnu.org/gnu/make/make-3.81.tar.gz](https://ftp.gnu.org/gnu/make/make-3.81.tar.gz)

tar xzvf make-3.81.tar.gz

cd make

./configure --program-suffix=3_81

make

sudo mv /usr/bin/make /usr/bin/make3_82

su -c "make install"

[https://tommumbles.wordpress.com/2011/02/17/installing-alternative-version-of-make/](https://tommumbles.wordpress.com/2011/02/17/installing-alternative-version-of-make/)