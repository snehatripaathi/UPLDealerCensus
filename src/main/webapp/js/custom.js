$(document).ready(function() {
	//survey List Table init 
	$('#surveyListTable').DataTable({responsive: true});
	
	//user List Table init 
	$('#userListTable').DataTable({responsive: true});
	
	//toggle hide show other input box on basis section on Q2 if - Other selected
	$("#other_agri_product_checkbox").change(function() {
		if($(this).is(':checked')){
			$('#other_agricultural_products_input').show();
		}
		else{
			$('#other_agricultural_products_input').hide();
		}
		
	});
	
	//toggle hide show other input box on basis section on Q2 if - Pesticide selected
	$("#pesticides_agri_product_checkbox").change(function() {
		if($(this).is(':checked')){
			$('#Q2_Pesticides_selection_dependancy').show();			
		}
		else{
			$('#Q2_Pesticides_selection_dependancy').hide();
		}		
	});
	
	//hide show section on basis of Q12 Yes No
	$('[name="business-owner"]').on('change', function(e) {
		if($('#not_business_owner_checkBox[type="radio"]').is(':checked')){
			$('#main_business_owner').show();
		}
		else{			
			$('#main_business_owner').hide();
		}
	});
	
	//toggle disable input box on basis section on Q16 if - other selected
	$('#designation_level').on('change', function() {
	  if(this.value == 'Others'){			
		$("#designation_level_other_name").prop( "disabled", false );
	  }
	  else{
		$("#designation_level_other_name").prop( "disabled", true );
		$("#designation_level_other_name").val("");
	  }
	});
	
	// Setting year values Q18
	for (i = new Date().getFullYear(); i > 1947; i--){
		$('#yearpicker').append($('<option />').val(i).html(i));
	}
	//Toggle Q19a on basis of Q19
	$('#percentage_business_wholesale').on('keypress change', function() {
		setTimeout(function(){
			if($('#percentage_business_wholesale').val() !== undefined && $('#percentage_business_wholesale').val() !== "" && $('#percentage_business_wholesale').val().length > -1){
				$("#depends_percentage_business_wholesale").show();
			}
			else{
				$("#depends_percentage_business_wholesale").hide();
			}
		}, 200);
		
	});
	$('.onlyNumberInput').on('keydown', 'input', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110])||(/65|67|86|88/.test(e.keyCode)&&(e.ctrlKey===true||e.metaKey===true))&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});

	//toggle hide show input box on basis section on Q19A if - other selected
	$('[name="different-agricultural-seasons"]').on('change', function() {
		if($('#different_agricultural_seasons_Yes[type="radio"]').is(':checked')){			
			$("#depend_on_19A").show();
		}
	  else{
		$("#depend_on_19A").hide();
		$("#depend_on_19A input").val("");
	  }
	});
	
	//toggle disable Question on basis section on Q20 if - Yes selected
	$('[name="other-category-of-products"]').on('change', function() {
		if($('#other_category_of_products_Yes[type="radio"]').is(':checked')){			
			$('#category_handled_container').show();
		}
	  else{
		$('#category_handled_container').hide();
		$("#category_handled tbody tr input").val("");
	  }
	});
	
	//Add new row to category handled table
	$('#category_handled tbody').on('focus','tr:nth-last-child(1) input', function(){
		totalnumber = $('#category_handled tbody tr').length + 1;			
		$('#category_handled tbody').append('<tr><td>'+ totalnumber +'</td><td><div class="has-default form-group"><input type="text" class="form-control" placeholder="Category Name"></div></td></tr>');
			
	});
	
	//toggle hide show input box on basis section on Q24 if - Yes selected
	$('[name="vehicle-for-delivery"]').on('change', function() {
		if($('#vehicle_for_delivery_Yes[type="radio"]').is(':checked')){			
			$("#vehicle_count").show();
		}
	  else{
		$("#vehicle_count").hide();
		$("#vehicle_count input").val("");
	  }
	});	
	
	//toggle hide show other input box on basis section on Q25 if - other selected
	$("#firm_have_other_accets").change(function() {
		if($(this).is(':checked')){
			$('#other_accets_input').show();
		}
		else{
			$('#other_accets_input').hide();
		}		
	});
	
	//toggle hide show other input box on basis section on Q25 if - other selected
	$("#firm_have_no_accets").change(function() {
		if($(this).is(':checked')){
			$('#jcompany_none_accets_hide').hide();			
		}
		else{
			$('#jcompany_none_accets_hide').show();
		}		
	});
	
	//toggle hide show other input box on basis section on Q26 if - Yes selected
	$('[name="computer-for-business"]').on('change', function() {
		if($('#computer_for_business_Yes[type="radio"]').is(':checked')){			
			$("#no_computers_used_hide").show();
		}
	  else{
		$("#no_computers_used_hide").hide();
		$("#no_computers_used_hide input").val("");
	  }
	});
	
	//toggle hide show other input box on basis section on Q28a if - Yes selected
	$('[name="credit-given-to-your-customers"]').on('change', function() {
		if($('#credit_to_customer_Yes[type="radio"]').is(':checked')){			
			$("#Q28A_dependancy").show();
		}
	  else{
		$("#Q28A_dependancy").hide();
		$("#Q28A_dependancy input").val("");
	  }
	});
	
	//toggle hide show input box on basis section on Q30 if - Yes selected
	$('[name="branches-or-subsidiaries"]').on('change', function() {
		if($('#firm_have_branches_Yes[type="radio"]').is(':checked')){			
			$("#firm_have_branches").show();
		}
	  else{
		$("#firm_have_branches").hide();
		$("#firm_have_branches input").val("");
	  }
	});	
	//Check box selection toggle Q33
	$('#seedSaleTable tr td:nth-last-child(1) input[type="checkbox"]').on('change', function() {
		if($(this).is(':checked')){
			$(this).parents('tr').find("input").each(function() {
				$(this).prop('checked', true);
			});
		}
		else{
			$(this).parents('tr').find("input").each(function() {
				$(this).prop('checked', false);
			});
		}
	});
	$('#seedSaleTable tr input[type="checkbox"]').on('change', function() {
		var checkbox_count = 0, i=0;
		$(this).parents('tr').find("input").each(function() {
			i+=1;
			if($(this).is(':checked') && i < 4 ){
				checkbox_count += 1;
			}
		});
		if(checkbox_count == 3){
			$(this).parents('tr').find("input").each(function() {
				$(this).prop('checked', true);
			});
		}
		else{
			$(this).parents('tr').children().last('td').find('input[type="checkbox"]').prop('checked', false);
		}
	});
	
	//Check box selection toggle Q34
	$('#sale_details_tbl tr td:nth-last-child(1) input[type="number"]').on('change', function() {
		var totalSalePercentage = 0;
		$('#sale_details_tbl tbody tr td:nth-last-child(1) input[type="number"]').each(function(){
			totalSalePercentage = parseInt( $(this).val()) + totalSalePercentage;
		});
		$('#total_Sales_Percantage').text(totalSalePercentage);
	});
	
	
//	//auto-complete init plug-in
//	$( ".select2-single" ).select2( {width: '100%'} );
});

//Step Changing function
$("#progressbar li").on("click", function() {
  if(!$(this).hasClass('active')){
	goToStep($(this).index()+1);
  }
});

function goToStep(tagetStep){
	$('#progressbar li').removeClass('active');
	$('#progressbar li:nth-child('+tagetStep+')').addClass('active');
	$('div [id|="step"]').hide();
	$('#step-'+tagetStep+'').show();
	$('html,body').animate({
	scrollTop: $(".main.main-raised").offset().top - 100},
	'slow');
}