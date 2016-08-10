### df命令

df命令用于显示磁盘分区上的可使用的磁盘空间. 默认使用单位为KB. 可以利用该命令来获取硬盘占用了多少空间, 目前还剩下多少空间等信息.

**选项**

> -a或--all : 包含全部的文件系统;
>
> --block-size=<区块大小>:以指定的区块大小来显示区块数目;
>
> -h或--human-readable: 以可读性较高的方式来显示信息;
>
> -H或--si: 与-h参数相同, 但在计算时是以1000bytes为换算单位而非1024Bytes;
>
> -i或--inodes: 显示inode的信息;
>
> -k或--kilobytes: 指定区块大小为1024字节;
>
> -l或--local: 仅显示本地端的文件系统;
>
>  -m或--megabytes: 指定区块大小为1048576字节;
>
> -no-sync: 在取得磁盘使用信息前, 先执行sync指令;
>
> -t<文件系统类型>或--type=<文件系统类型>: 仅显示指定文件系统类型的磁盘信息;
>
> -T或--print-type: 显示文件系统的类型;
>
> -x<文件系统类型>或--exclude-type=<文件系统类型>: 不要显示指定文件系统类型的磁盘信息;
>
> --version: 显示版本信息

**实例**

查看系统磁盘设备, 默认是kb为单位:

```markdown
Filesystem                   1K-blocks     Used Available Use% Mounted on
/dev/mapper/vagrant--vg-root  19229144  4719428  13509884  26% /
none                                 4        0         4   0% /sys/fs/cgroup
udev                           1011548       12   1011536   1% /dev
tmpfs                           205000      380    204620   1% /run
none                              5120        0      5120   0% /run/lock
none                           1024992        0   1024992   0% /run/shm
none                            102400        0    102400   0% /run/user
/dev/sda1                       240972   112563    115968  50% /boot
vagrant                      189793276 26921380 162871896  15% /vagrant
```

使用`-h`选项以kb以上的单位来显示, 可读性高:

```markdown
Filesystem                    Size  Used Avail Use% Mounted on
/dev/mapper/vagrant--vg-root   19G  4.6G   13G  26% /
none                          4.0K     0  4.0K   0% /sys/fs/cgroup
udev                          988M   12K  988M   1% /dev
tmpfs                         201M  380K  200M   1% /run
none                          5.0M     0  5.0M   0% /run/lock
none                         1001M     0 1001M   0% /run/shm
none                          100M     0  100M   0% /run/user
/dev/sda1                     236M  110M  114M  50% /boot
vagrant                       182G   26G  156G  15% /vagrant
```

查看全部文件系统:

```markdown
Filesystem                   1K-blocks     Used Available Use% Mounted on
/dev/mapper/vagrant--vg-root  19229144  4719428  13509884  26% /
proc                                 0        0         0    - /proc
sysfs                                0        0         0    - /sys
none                                 4        0         4   0% /sys/fs/cgroup
none                                 0        0         0    - /sys/fs/fuse/connections
none                                 0        0         0    - /sys/kernel/debug
none                                 0        0         0    - /sys/kernel/security
udev                           1011548       12   1011536   1% /dev
devpts                               0        0         0    - /dev/pts
tmpfs                           205000      380    204620   1% /run
none                              5120        0      5120   0% /run/lock
none                           1024992        0   1024992   0% /run/shm
none                            102400        0    102400   0% /run/user
none                                 0        0         0    - /sys/fs/pstore
cgroup                               0        0         0    - /sys/fs/cgroup/cpuset
cgroup                               0        0         0    - /sys/fs/cgroup/cpu
cgroup                               0        0         0    - /sys/fs/cgroup/cpuacct
cgroup                               0        0         0    - /sys/fs/cgroup/memory
cgroup                               0        0         0    - /sys/fs/cgroup/devices
cgroup                               0        0         0    - /sys/fs/cgroup/freezer
cgroup                               0        0         0    - /sys/fs/cgroup/blkio
cgroup                               0        0         0    - /sys/fs/cgroup/perf_event
cgroup                               0        0         0    - /sys/fs/cgroup/hugetlb
/dev/sda1                       240972   112563    115968  50% /boot
rpc_pipefs                           0        0         0    - /run/rpc_pipefs
systemd                              0        0         0    - /sys/fs/cgroup/systemd
vagrant                      189793276 26921380 162871896  15% /vagrant
```

