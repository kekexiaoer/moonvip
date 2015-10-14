(function () {
 CKEDITOR.plugins.add('UpLink',
{    init: function(editor)
    {
        var pluginName = 'UpLink';
        CKEDITOR.dialog.add(pluginName, this.path + 'dialogs/uplink.js');
        editor.addCommand(pluginName, new CKEDITOR.dialogCommand(pluginName));
        editor.ui.addButton('UpLink',
            {
                label: '上传链接',
                command: pluginName,
				icon: CKEDITOR.plugins.getPath('UpLink') + 'uplink.png'
          });
    }
}); 
})();