using Microsoft.OData.Edm;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebAPI.Model
{
    [Table("HoaDon")]
    public class HoaDon
    {
        [Key]
        [Required]
        public int ID_HoaDon { get; set; }

        [DisplayFormat(DataFormatString ="{0:dd//MM//yyyy}")]
        public DateTime NgayLap { get; set; }
        public int TongTien { get; set; }
        public int ID_Account { get; set; }

        [ForeignKey("ID_Account")]
        public virtual Account? Account { get; set; }
        public virtual ICollection<ChiTietHoaDon>? ChiTietHoaDons { get; set; }
    }
}
