�}~  � �=΃�JS:$�����Y�b�s��B�E�F�H|� D.Pl�zb0�2�'�<�0ݖQ�4�(RE��^���7ծ�:yc���^P��~C�Q)�O������b���۳�l�F�wLm{��K;�+;�kY:��G�Gy�"L��^E���AKMs�����qN�E2/�Fڙ�>͓~c��-�4�]��7#y��j���h��ShЪW�|Fc�1��N"�џ�A��~sԮa7�Å�(Ǚ�װ�b�9ЋvQ��w@eW߾�r|Q]X�E/�(��_/�c<en�*;�p�? 8C�*�\h$FR��@�|���c$ƃB�k*-�re �~;� nrL,��",� �G�,��Ҭx�y����� � �)� w�>O� �Np�����L��ͼ���Rz�Jto�+�)����R�j,��DH�����jE����Jc�;S�����
QF%�wE�Q�O���j�f��Y�Fz$�z��`�V�P5!�V�z@:��象未必已经被回收
        System.out.println(pr.get());
        //jvm回收掉对象的时候，会把该引用添加到队列中
        System.gc();
        System.out.println(pr.get());

    }
}
