�}�~  t ���ӥ��$�����Y�b�s�@E��'���v��|�ZdV�Mf����m,���'x-���Imej��O�����Q���!��1�94«/�n�{^cI+�)�n.H7	{�}�p�ώC�g�ڕ#(Q7�(��"��#��f|�.jw{�ր�'qq���>�V�3�q���&+5i�죾����0�wpɗ@d˥o����ՙ�g�z��Ai\�K<c���j}��:�p������ЬbӚW.�\u��˒�yO�����'t%D���b�ۆ)�T�a�v6c�?�
n�*;�p�? 8C�*�\h$FR��@�|���c$ƃB�k*-�re �~;� nrL,��",� �G�,��Ҭx�y����� � �)� w�>O� �Np�����L��ͼ���Rz�Jto�+�)����R�j,��DH�����jE����Jc�;S�����
QF%�wE�Q�O���j�f��Y�Fz$�z��`�V�P5!�V�z@:变为1后的值
     */
    public static int do1(int i,int pos){
        return i|(1<<pos-1);
    }

    public static void main(String[] args) {
        int i = 0x01;
        Integer i1 = do1(i, 3);
        System.out.println(Integer.toBinaryString(i1));
    }

}
