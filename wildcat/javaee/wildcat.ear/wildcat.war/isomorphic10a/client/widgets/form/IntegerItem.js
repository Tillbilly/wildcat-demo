/*
 * Isomorphic SmartClient
 * Version v10.0p_2015-01-04 (2015-01-04)
 * Copyright(c) 1998 and beyond Isomorphic Software, Inc. All rights reserved.
 * "SmartClient" is a trademark of Isomorphic Software, Inc.
 *
 * licensing@smartclient.com
 *
 * http://smartclient.com/license
 */

 





//>	@class	IntegerItem
// FormItem intended for input integer numbers.
//
// @visibility external
//<
isc.ClassFactory.defineClass("IntegerItem", "TextItem");
isc.IntegerItem.addProperties({
    
    type: "integer"

});

isc.IntegerItem.addMethods({

    //> @method integerItem.getValueAsInteger()
    // Return the value tracked by this form item as a Integer.  If the value cannot
    // be parsed to an int that matches the original value, null will be returned.
    //
    // @return (Integer) value of this element
    //
    // @see method:FormItem.getValue
    // @visibility external
    //<
    
    getValueAsInteger : function () {
        var origValue   = this.getValue(),
            parsedValue = parseInt(origValue);
        
        return isNaN(parsedValue) || parsedValue.toString() != origValue ? null : parsedValue;
    }

});
