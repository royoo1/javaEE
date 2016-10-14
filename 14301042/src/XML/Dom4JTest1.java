package XML;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * dom4j���ѧϰ ʹ��dom4j��ܴ���xml�ĵ����������
 * 
 */
public class Dom4JTest1
{
    public static void main(String[] args) throws Exception
    {
        // ��һ�ַ�ʽ�������ĵ�����������Ԫ��
        // �����ĵ�:ʹ����һ��Helper��
        Document document = DocumentHelper.createDocument();
        // �������ڵ㲢��ӽ��ĵ�
        Element root = DocumentHelper.createElement("student");
        document.setRootElement(root);
        // �ڶ��ַ�ʽ:�����ĵ��������ĵ��ĸ�Ԫ�ؽڵ�
        Element root2 = DocumentHelper.createElement("student");
        Document document2 = DocumentHelper.createDocument(root2);

        // �������
        root2.addAttribute("name", "zhangsan");
        // ����ӽڵ�:add֮��ͷ������Ԫ��
        Element helloElement = root2.addElement("hello");
        Element worldElement = root2.addElement("world");

        helloElement.setText("hello Text");
        worldElement.setText("world text");

        // ���
        // ���������̨
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.write(document);

        // ������ļ�
        // ��ʽ
        OutputFormat format = new OutputFormat("    ", true);// ��������Ϊ4���ո񣬲�������һ��Ϊtrue
        XMLWriter xmlWriter2 = new XMLWriter(
                new FileOutputStream("student.xml"), format);
        xmlWriter2.write(document2);

        // ��һ�������ʽ���ǵ�Ҫ����flush()����,����������ļ�����ʾ�հ�
        XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("student2.xml"),
                format);
        xmlWriter3.write(document2);
        xmlWriter3.flush();
        // close()����Ҳ����

    }
}