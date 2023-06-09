package com.company;

public class ComboBox {
    private int combo_id;
    private String combo_name;
    private String content;
    private int combo_cost;

    public int getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(int combo_id) {
        this.combo_id = combo_id;
    }

    public String getCombo_name() {
        return combo_name;
    }

    public void setCombo_name(String combo_name) {
        this.combo_name = combo_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCombo_cost() {
        return combo_cost;
    }

    public void setCombo_cost(int combo_cost) {
        this.combo_cost = combo_cost;
    }

    public ComboBox() {
    }

    public ComboBox(int combo_id, String combo_name, String content, int combo_cost) {
        this.combo_id = combo_id;
        this.combo_name = combo_name;
        this.content = content;
        this.combo_cost = combo_cost;
    }
}
