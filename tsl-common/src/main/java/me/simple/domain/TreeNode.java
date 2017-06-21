package me.simple.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by chensheng on 17/6/19.
 */
public class TreeNode {
    private int id;
    private int pid;
    private String text;
    private String state;
    private boolean checked;
    private boolean disabled;
    private Map<String,Object> attributes;
    private List<TreeNode> children;

    public TreeNode() {
    }

    public TreeNode(int id) {
        this.id = id;
    }

    public TreeNode(int id, String text, List<TreeNode> children) {
        this.id = id;
        this.text = text;
        this.children = children;
    }

    public TreeNode(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
