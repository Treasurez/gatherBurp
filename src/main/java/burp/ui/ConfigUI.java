package burp.ui;

import burp.IBurpExtenderCallbacks;
import burp.bean.ConfigBean;
import burp.utils.UrlCacheUtil;
import burp.utils.Utils;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static burp.dao.ConfigDao.*;

public class ConfigUI implements UIHandler {
    private static final List<LogEntry> data = new ArrayList<>();
    public AbstractTableModel dataModel = new ConfigModel();
    private JPanel panel;
    private JPanel configPanel;
    private JLabel dnslogLabel;
    private JTextField dnslogTextField;
    private JLabel ipLabel;
    private JTextField ipTextField;
    private JLabel toolNameLabel;
    private JTextField toolNameTextField;
    private JButton toolButton;
    private JTextField toolArgvTextField;
    private JButton ipButton;
    private JButton dnslogButton;
    private JLabel toolArgvLabel;
    private JButton refershButton;
    private JButton deleteSelectButton;
    private JButton clearCacheButton;
    private JButton resetUrl;
    private JTable configTable;
    private JScrollPane configPanelDownJscrollPanel;
    private JPanel configPanelTop;
    private JPanel configPanelDown;

    private void setupUI() {
        // 创建主面板
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));

        // 创建配置面板
        configPanel = new JPanel();
        configPanel.setLayout(new BorderLayout(0, 0));
        panel.add(configPanel, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null, 0, false));

        // 配置顶部面板
        configPanelTop = new JPanel();
        configPanelTop.setLayout(new GridLayoutManager(4, 9, new Insets(10, 10, 10, 10), 5, 5));  // 增加间距
        configPanel.add(configPanelTop, BorderLayout.NORTH);

        // DNS日志配置行
        dnslogLabel = new JLabel();
        dnslogLabel.setText("DNS日志");
        configPanelTop.add(dnslogLabel, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                null, null, null, 0, false));

        dnslogTextField = new JTextField();
        configPanelTop.add(dnslogTextField, new GridConstraints(0, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED,
                null, new Dimension(250, -1), null, 0, false));

        dnslogButton = new JButton();
        dnslogButton.setText("保存配置");
        configPanelTop.add(dnslogButton, new GridConstraints(0, 4, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        // IP配置行
        ipLabel = new JLabel();
        ipLabel.setText("IP地址");
        configPanelTop.add(ipLabel, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                null, null, null, 0, false));

        ipTextField = new JTextField();
        configPanelTop.add(ipTextField, new GridConstraints(1, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED,
                null, new Dimension(250, -1), null, 0, false));

        ipButton = new JButton();
        ipButton.setText("保存配置");
        configPanelTop.add(ipButton, new GridConstraints(1, 4, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        // 工具配置行
        toolNameLabel = new JLabel();
        toolNameLabel.setText("工具名称");
        configPanelTop.add(toolNameLabel, new GridConstraints(2, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                null, null, null, 0, false));

        toolNameTextField = new JTextField();
        configPanelTop.add(toolNameTextField, new GridConstraints(2, 1, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED,
                null, new Dimension(150, -1), null, 0, false));

        toolArgvLabel = new JLabel();
        toolArgvLabel.setText("工具参数");
        configPanelTop.add(toolArgvLabel, new GridConstraints(2, 2, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED,
                null, null, null, 0, false));

        toolArgvTextField = new JTextField();
        configPanelTop.add(toolArgvTextField, new GridConstraints(2, 3, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED,
                null, new Dimension(250, -1), null, 0, false));

        toolButton = new JButton();
        toolButton.setText("保存配置");
        configPanelTop.add(toolButton, new GridConstraints(2, 4, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        // 按钮行
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));  // 使用FlowLayout，左对齐，间距10
        configPanelTop.add(buttonPanel, new GridConstraints(3, 0, 1, 9,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        refershButton = new JButton();
        refershButton.setText("刷新");
        buttonPanel.add(refershButton);

        deleteSelectButton = new JButton();
        deleteSelectButton.setText("删除选中");
        buttonPanel.add(deleteSelectButton);

        clearCacheButton = new JButton();
        clearCacheButton.setText("清除缓存");
        buttonPanel.add(clearCacheButton);

        // 新增按钮
        resetUrl = new JButton("重置重复性校验");
        buttonPanel.add(resetUrl);

        // 配置下部面板（表格）
        configPanelDown = new JPanel();
        configPanelDown.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        configPanel.add(configPanelDown, BorderLayout.CENTER);

        configPanelDownJscrollPanel = new JScrollPane();
        configPanelDown.add(configPanelDownJscrollPanel, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null, 0, false));

        configTable = new JTable(dataModel);
        configTable.setRowHeight(25);  // 设置表格行高
        configTable.setFillsViewportHeight(true);  // 表格填充视窗
        configPanelDownJscrollPanel.setViewportView(configTable);
    }

    private void setupData() {
        ConfigBean dnsconfig = getConfig("config", "dnslog");
        dnslogTextField.setText(dnsconfig.getValue());

        ConfigBean ipconfig = getConfig("config", "ip");
        ipTextField.setText(ipconfig.getValue());

        toolNameTextField.setText("sqlmap");
        toolArgvTextField.setText("python sqlmap.py -r {request} -u {url} -h {host}");

        List<ConfigBean> toolParam = getToolConfig();
        for (ConfigBean config : toolParam) {
            addData(config.getType(), config.getValue());
        }

        refershButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ConfigBean> toolParam = getToolConfig();
                data.clear();
                for (ConfigBean config : toolParam) {
                    addData(config.getType(), config.getValue());
                }
                dataModel.fireTableDataChanged();
            }
        });

        deleteSelectButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int[] selectedRows = configTable.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedRow = selectedRows[i];
                    String type = (String) configTable.getValueAt(selectedRow, 1);
                    deleteToolConfig(type);
                    data.remove(selectedRow);
                    dataModel.fireTableRowsDeleted(selectedRow, selectedRow);
                    dataModel.fireTableDataChanged();
                }
            }
        });
        clearCacheButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // mark
                // 弹出提示框
                boolean deleteReqFile = Utils.deleteReqFile();
                if (deleteReqFile){
                    JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        dnslogButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String module = "config";
                String dns = dnslogTextField.getText();
                ConfigBean config = new ConfigBean(module, "dnslog", dns);
                saveConfig(config);
                FastjsonUI.dnslog = dns;
                Log4jUI.dns = dns;
                // 弹窗提示
                JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ipButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String module = "config";
                String ip = ipTextField.getText();
                ConfigBean config = new ConfigBean(module, "ip", ip);
                saveConfig(config);
                FastjsonUI.ip = ip;
                Log4jUI.ip = ip;
                JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        toolButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String module = "tool";
                String type = toolNameTextField.getText();
                String value = toolArgvTextField.getText();
                ConfigBean config = new ConfigBean(module, type, value);
                saveConfig(config);
                addData(type, value);
                JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        resetUrl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UrlCacheUtil.resetAllCaches();
                JOptionPane.showMessageDialog(null, "重置成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    // 添加数据
    public void addData(String key, String value) {
        synchronized (data) {
            data.add(new LogEntry(data.size() + 1, key, value));
            dataModel.fireTableDataChanged();
            dataModel.fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }
    }

    @Override
    public void init() {
        setupUI();
        setupData();
    }

    @Override
    public JPanel getPanel(IBurpExtenderCallbacks callbacks) {
        return panel;
    }

    @Override
    public String getTabName() {
        return "Setting";
    }

    // 表格模型
    static class ConfigModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            LogEntry dataEntry = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return dataEntry.id;
                case 1:
                    return dataEntry.key;
                case 2:
                    return dataEntry.value;
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "id";
                case 1:
                    return "key";
                case 2:
                    return "value";
                default:
                    return null;
            }
        }
    }

    // 表格实体类
    public static class LogEntry {
        private final String key;
        private final String value;
        private int id;

        public LogEntry(int id, String key, String value) {
            this.id = id;
            this.key = key;
            this.value = value;
        }

        public LogEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
