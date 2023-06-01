using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebAPI.Model
{
    [Table("Cart")]
    public class Cart
    {
        [Key]
        [Required]
        public int ID_Cart { get; set; }
        public int ID_Account { get; set; }
        public int ID_Sach { get; set; }
        public int SoLuong { get; set; }
        public int TongTien { get; set; }

        [ForeignKey("ID_Account")]
        public virtual Account? Account { get; set; }

        [ForeignKey("ID_Sach")]
        public virtual Sach? Sach { get; set; }
    }
}
