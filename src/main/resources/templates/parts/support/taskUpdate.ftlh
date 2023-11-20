<label class="input-group-text">Add defects:</label>
<div class="input-group input-group-sm" style="width:75px">
    <input type="number"
           class="form-control col-xs-2"
           min="0"
           value="0"
           name="defectValue"
           id="defect_value_input"
           required>
</div>
<label class="input-group-text">Reverts:</label>
<div class="input-group-text">
    <input class="form-check-input mt-0"
           type="checkbox"
           name="returnValue"
           id="return_value_checkbox">
</div>

<label class="input-group-text" for="devHelp">DevHelp:</label>
<div class="input-group input-group-sm" style="width:150px">
    <select class="form-select" id="dev_help_select" name="helpValue">
        <option value="0">-</option>
        <option value="-1">NO</option>
        <option value="1">YES</option>
    </select>
</div>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<button type="submit" class="btn btn-outline-success btn-sm" >Update Task</button>